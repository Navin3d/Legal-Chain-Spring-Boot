package gmc.project.blockchain.legalchain.vaultservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gmc.project.blockchain.legalchain.vaultservice.models.ResponseModel;
import gmc.project.blockchain.legalchain.vaultservice.models.parivahan.DocumentRequestModel;
import gmc.project.blockchain.legalchain.vaultservice.models.parivahan.LicenseInfo;
import gmc.project.blockchain.legalchain.vaultservice.models.parivahan.RequestLicenseModel;
import gmc.project.blockchain.legalchain.vaultservice.services.DocumentTransferService;

@RequestMapping(path = "/transfer")
@RestController
public class TransferController {
	
	@Autowired
	private DocumentTransferService docTransferService;
	
	@GetMapping("/{docType}/{email}")
	private ResponseEntity<ResponseModel<String>> initiate2fAuth(@PathVariable String docType, @PathVariable String email) {
		ResponseModel<String> body = new ResponseModel<String>();
		HttpStatus status = HttpStatus.OK;
		try {
			docTransferService.initiate2fAuth(docType, email);
			body.setMessage("OTP sent Successfully.");
			body.setStatus(1);
		} catch(Exception e) {
			e.printStackTrace();
			body.setMessage("Error initializinf 2f");
			body.setStatus(0);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			body.setError(e.getMessage());
		}
		return ResponseEntity.status(status).body(body);
	}
	
	@PostMapping("/req")
	private ResponseEntity<ResponseModel<Object>> requestDocument(@RequestBody DocumentRequestModel doc) {
		ResponseModel<Object> body = new ResponseModel<Object>();
		HttpStatus status = HttpStatus.OK;
		try {
			body = docTransferService.requestDocument(doc);
		} catch(Exception e) {
			e.printStackTrace();
			body.setMessage("Error requesting document.");
			body.setStatus(0);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			body.setError(e.getMessage());
		}
		return ResponseEntity.status(status).body(body);
	}
	
	@PostMapping("/license")
	private ResponseEntity<LicenseInfo> transferDocument(@RequestBody RequestLicenseModel doc) {
		LicenseInfo body = new LicenseInfo();
		HttpStatus status = HttpStatus.OK;
		try {
			body = docTransferService.transferDrivingLicense(doc);
		} catch(Exception e) {
			e.printStackTrace();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return ResponseEntity.status(status).body(body);
	}

}
