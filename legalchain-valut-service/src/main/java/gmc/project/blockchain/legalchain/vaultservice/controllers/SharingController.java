package gmc.project.blockchain.legalchain.vaultservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gmc.project.blockchain.legalchain.vaultservice.models.SharringModel;
import gmc.project.blockchain.legalchain.vaultservice.services.SharingService;

@RequestMapping(path = "/share")
@RestController
public class SharingController {

	@Autowired
	private SharingService sharingService;

	@PostMapping
	private ResponseEntity<String> shareRecord(@RequestBody SharringModel shareModel) {
		sharingService.shareDocument(shareModel);
		return ResponseEntity.status(HttpStatus.OK).body("Document shared successfully.");
	}

}
