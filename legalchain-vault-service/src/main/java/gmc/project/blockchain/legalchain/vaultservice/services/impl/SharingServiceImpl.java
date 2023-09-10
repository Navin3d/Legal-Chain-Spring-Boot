package gmc.project.blockchain.legalchain.vaultservice.services.impl;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import gmc.project.blockchain.legalchain.vaultservice.dao.SharingDao;
import gmc.project.blockchain.legalchain.vaultservice.entities.RecordEntity;
import gmc.project.blockchain.legalchain.vaultservice.entities.SharingEntity;
import gmc.project.blockchain.legalchain.vaultservice.entities.UserEntity;
import gmc.project.blockchain.legalchain.vaultservice.models.SharringModel;
import gmc.project.blockchain.legalchain.vaultservice.services.RecordService;
import gmc.project.blockchain.legalchain.vaultservice.services.SharingService;
import gmc.project.blockchain.legalchain.vaultservice.services.UserService;
import gmc.project.blockchain.legalchain.vaultservice.utils.DateUtill;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SharingServiceImpl implements SharingService {

	private final SharingDao shareDao;
	private final UserService userService;
	private final RecordService recordService;

	@Override
	public void shareDocument(SharringModel shareModel) {
		Set<RecordEntity> sharredRecords = new HashSet<>();
		Set<UserEntity> allowedUsers = new HashSet<>();
		shareModel.getRecordIds().forEach(recordId -> {
			RecordEntity recordEnrity = recordService.findOne(recordId);
			shareModel.getUserIds().forEach(userId -> {
				UserEntity user = userService.findOne(userId);
				user.getRecordsShared().add(recordEnrity);
				UserEntity savedUser = userService.save(user);
				allowedUsers.add(savedUser);
			});
			sharredRecords.add(recordEnrity);
		});
		SharingEntity sharingEntity = new SharingEntity();
		sharingEntity.setRecord(sharredRecords);
		sharingEntity.setSharedWith(allowedUsers);
		sharingEntity.setSharedAt(LocalDate.now());
		sharingEntity.setShareUntil(DateUtill.parse(shareModel.getShareUntil()));
		shareDao.insert(sharingEntity);
	}

}
