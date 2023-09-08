package gmc.project.blockchain.legalchain.authservice.dao;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import gmc.project.blockchain.legalchain.authservice.entities.UserEntity;

public interface UserDao extends MongoRepository<UserEntity, String> {

	public Optional<UserEntity> findByUsername(String username);
	public Optional<UserEntity> findByMobileNumber(String mobileNumber);
	public Optional<UserEntity> findByEmail(String email);
	public Optional<UserEntity> findByAadharNumber(String aadharNumber);
	
}
