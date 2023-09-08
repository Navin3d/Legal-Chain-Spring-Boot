package gmc.project.blockchain.legalchain.vaultservice.services.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gmc.project.blockchain.legalchain.vaultservice.dao.RecordDao;
import gmc.project.blockchain.legalchain.vaultservice.models.InsertRecordModel;
import gmc.project.blockchain.legalchain.vaultservice.services.RecordService;

@Service
public class RecordServiceImpl implements RecordService {
	
	@Autowired
	private RecordDao recordDao;
	
	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public void pinRecord(InsertRecordModel recordModel) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
				
	}

}
