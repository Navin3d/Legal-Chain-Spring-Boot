package gmc.project.blockchain.legalchain.vaultservice.services.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gmc.project.blockchain.legalchain.vaultservice.dao.RecordDao;
import gmc.project.blockchain.legalchain.vaultservice.entities.RecordEntity;
import gmc.project.blockchain.legalchain.vaultservice.entities.UserEntity;
import gmc.project.blockchain.legalchain.vaultservice.models.InsertRecordModel;
import gmc.project.blockchain.legalchain.vaultservice.services.RecordService;
import gmc.project.blockchain.legalchain.vaultservice.services.UserService;
import gmc.project.blockchain.legalchain.vaultservice.utils.DateUtill;

@Service
public class RecordServiceImpl implements RecordService {
	
	@Autowired
	private RecordDao recordDao;
	
	@Autowired
	private UserService userService;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	@Override
	public RecordEntity findOne(String id) {
		RecordEntity foundRecord = recordDao.findById(id).orElse(null);
		return foundRecord;
	}

	@Override
	public void pinRecord(InsertRecordModel recordModel) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserEntity recordOwner = userService.findOne(recordModel.getOwnedBy());
		RecordEntity record = new RecordEntity();
		record.setId(recordModel.getId());
		record.setInsertedAt(DateUtill.parseDT(recordModel.getInsertedAt()));
		record.setOwnedBy(recordOwner);
		RecordEntity savedRecord = recordDao.insert(record);
		recordOwner.getRecordsOwned().add(savedRecord);
		userService.save(recordOwner);
	}

	@Override
	public RecordEntity save(RecordEntity recordEntity) {
		RecordEntity savedRecord = recordDao.save(recordEntity);
		return savedRecord;
	}

}
