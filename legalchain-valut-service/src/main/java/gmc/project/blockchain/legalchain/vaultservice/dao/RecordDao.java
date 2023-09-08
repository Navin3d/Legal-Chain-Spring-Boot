package gmc.project.blockchain.legalchain.vaultservice.dao;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import gmc.project.blockchain.legalchain.vaultservice.entities.RecordEntity;
import gmc.project.blockchain.legalchain.vaultservice.entities.UserEntity;

public interface RecordDao extends MongoRepository<RecordEntity, String> {

	public Optional<UserEntity> findByUsername(String username);
	public Optional<UserEntity> findByMobileNumber(String mobileNumber);
	public Optional<UserEntity> findByEmail(String email);
	public Optional<UserEntity> findByAadharNumber(String aadharNumber);
	
}
