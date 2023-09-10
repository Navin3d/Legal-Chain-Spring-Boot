package gmc.project.blockchain.legalchain.vaultservice.services;

import gmc.project.blockchain.legalchain.vaultservice.entities.UserEntity;

public interface UserService {
	public UserEntity findOne(String uniqeId);
	public UserEntity save(UserEntity userEntity);
}
