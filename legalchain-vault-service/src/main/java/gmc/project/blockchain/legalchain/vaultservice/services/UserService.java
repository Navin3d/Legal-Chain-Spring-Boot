package gmc.project.blockchain.legalchain.vaultservice.services;

import gmc.project.blockchain.legalchain.vaultservice.entities.UserEntity;
import gmc.project.blockchain.legalchain.vaultservice.models.UserRecordsModel;

public interface UserService {
	public UserEntity findOne(String uniqeId);
	public UserEntity save(UserEntity userEntity);
	
	public UserRecordsModel getUserRecords(String uname);
}
