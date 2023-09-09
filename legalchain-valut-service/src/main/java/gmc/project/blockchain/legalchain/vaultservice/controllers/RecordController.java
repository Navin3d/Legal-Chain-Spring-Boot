package gmc.project.blockchain.legalchain.vaultservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gmc.project.blockchain.legalchain.vaultservice.models.InsertRecordModel;
import gmc.project.blockchain.legalchain.vaultservice.services.RecordService;

@RequestMapping(path = "/record")
@RestController
public class RecordController {
	
	@Autowired
	private RecordService recordService;
	
	@PostMapping(path = "/pin")
	private ResponseEntity<String> uploadedRecord(@RequestBody InsertRecordModel insertRecordModel) {
		recordService.pinRecord(insertRecordModel);
		return ResponseEntity.status(HttpStatus.OK).body("The record is stored in blockchain successfully.");
	}

}
