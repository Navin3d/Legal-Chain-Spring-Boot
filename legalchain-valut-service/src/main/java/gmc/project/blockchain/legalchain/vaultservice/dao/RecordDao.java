package gmc.project.blockchain.legalchain.vaultservice.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import gmc.project.blockchain.legalchain.vaultservice.entities.RecordEntity;

public interface RecordDao extends MongoRepository<RecordEntity, String> {
	
}
