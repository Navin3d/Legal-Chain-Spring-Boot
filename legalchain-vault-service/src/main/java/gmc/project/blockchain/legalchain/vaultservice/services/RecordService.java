package gmc.project.blockchain.legalchain.vaultservice.services;

import gmc.project.blockchain.legalchain.vaultservice.entities.RecordEntity;
import gmc.project.blockchain.legalchain.vaultservice.models.InsertRecordModel;

public interface RecordService {
	public RecordEntity findOne(String id);
	public void pinRecord(InsertRecordModel recordModel);
	public RecordEntity save(RecordEntity recordEntity);
}
