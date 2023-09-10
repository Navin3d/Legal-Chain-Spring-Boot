package gmc.project.blockchain.legalchain.vaultservice.dao;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import gmc.project.blockchain.legalchain.vaultservice.entities.SharingEntity;

public interface SharingDao extends MongoRepository<SharingEntity, String> {

	public Optional<SharingEntity> findByShareUntil(LocalDate shareUntil);
	
}
