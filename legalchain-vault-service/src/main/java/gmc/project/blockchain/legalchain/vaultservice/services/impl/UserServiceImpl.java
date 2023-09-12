package gmc.project.blockchain.legalchain.vaultservice.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gmc.project.blockchain.legalchain.vaultservice.dao.UserDao;
import gmc.project.blockchain.legalchain.vaultservice.entities.RecordEntity;
import gmc.project.blockchain.legalchain.vaultservice.entities.UserEntity;
import gmc.project.blockchain.legalchain.vaultservice.models.BlockchainDocModel;
import gmc.project.blockchain.legalchain.vaultservice.models.BlockchainResponse;
import gmc.project.blockchain.legalchain.vaultservice.models.DocumentModel;
import gmc.project.blockchain.legalchain.vaultservice.models.GetDocumentsModel;
import gmc.project.blockchain.legalchain.vaultservice.models.UserRecordsModel;
import gmc.project.blockchain.legalchain.vaultservice.services.BlockchainServiceFeignClient;
import gmc.project.blockchain.legalchain.vaultservice.services.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BlockchainServiceFeignClient blockchainService;

	@Override
	public UserEntity findOne(String uniqueId) {
		UserEntity foundUser = null;
		if(uniqueId.contains("@")) {
			log.info("The entered unique id is Email.");
			foundUser = userDao.findByEmail(uniqueId).orElse(null);
		} else {
			try {
				Long.valueOf(uniqueId);
				if(uniqueId.length() == 12) {
					log.info("The entered unique id is Aadhar Id.");
					foundUser = userDao.findByAadharNumber(uniqueId).orElse(null);
				}
				if(uniqueId.length() == 10) {
					log.info("The entered unique id is Mobile Number.");
					foundUser = userDao.findByMobileNumber(uniqueId).orElse(null);
				}
			} catch(NumberFormatException e) {
				log.info("The entered unique id is Id.");
				foundUser = userDao.findById(uniqueId).orElse(null);
			}
		}
		if(foundUser == null) {
			log.info("The entered unique id is username.");
			foundUser = userDao.findByUsername(uniqueId).orElse(null);
		}
		if(foundUser == null) {
			log.error("The user with id {} not found.", uniqueId);
			throw new RuntimeException(uniqueId);
		}
		return foundUser;
	}

	@Override
	public UserEntity save(UserEntity userEntity) {
		UserEntity saveduser = userDao.save(userEntity);
		return saveduser;
	}

	@Override
	public UserRecordsModel getUserRecords(String uname) {
		UserRecordsModel returnValue = new UserRecordsModel();
		UserEntity foundUser = findOne(uname);
		GetDocumentsModel owned = new GetDocumentsModel(uname);
		GetDocumentsModel shared = new GetDocumentsModel(uname);
		for(RecordEntity record : foundUser.getRecordsOwned())
			owned.getDocumentIds().add(record.getId());
		for(RecordEntity record : foundUser.getRecordsShared())
			shared.getDocumentIds().add(record.getId());
		log.error("recordOwned {} - {}.", owned, shared);
		BlockchainResponse ownedResponse = blockchainService.getDocuments(owned);
		BlockchainResponse sharedResponse = blockchainService.getDocuments(shared);
		for(BlockchainDocModel recModel : ownedResponse.getData()) {
			 DocumentModel doc = recModel.getDocumentData();
			 doc.setId(recModel.getDocumentId());
			 returnValue.getOwnedRecords().add(doc);
		}
		for(BlockchainDocModel recModel : sharedResponse.getData()) {
			 DocumentModel doc = recModel.getDocumentData();
			 doc.setId(recModel.getDocumentId());
			 returnValue.getSharedRecords().add(doc);
		}
		return returnValue;
	}

}
