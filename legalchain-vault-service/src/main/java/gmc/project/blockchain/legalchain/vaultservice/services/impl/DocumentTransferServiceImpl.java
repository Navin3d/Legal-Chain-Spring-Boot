package gmc.project.blockchain.legalchain.vaultservice.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import gmc.project.blockchain.legalchain.vaultservice.config.ParivahanServerConfig;
import gmc.project.blockchain.legalchain.vaultservice.entities.UserEntity;
import gmc.project.blockchain.legalchain.vaultservice.models.MailingModel;
import gmc.project.blockchain.legalchain.vaultservice.models.ResponseModel;
import gmc.project.blockchain.legalchain.vaultservice.models.parivahan.DocumentRequestModel;
import gmc.project.blockchain.legalchain.vaultservice.models.parivahan.LicenseInfo;
import gmc.project.blockchain.legalchain.vaultservice.models.parivahan.RequestLicenseModel;
import gmc.project.blockchain.legalchain.vaultservice.services.DocumentTransferService;
import gmc.project.blockchain.legalchain.vaultservice.services.ProphetServiceFeignClient;
import gmc.project.blockchain.legalchain.vaultservice.services.UserService;
import gmc.project.blockchain.legalchain.vaultservice.utils.Helpers;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DocumentTransferServiceImpl implements DocumentTransferService {
	
	@Autowired
	private ParivahanServerConfig parivahanConfig;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProphetServiceFeignClient prophetService;
	
	private RestTemplate restTemplate = new RestTemplate();

	@Override
	public void initiate2fAuth(String documentType, String email) {
		UserEntity foundUser = userService.findOne(email);
		Integer otp = Helpers.generateRandomSixDigitNumber();
		foundUser.setOtp(otp.toString());
		foundUser.setIsOTPUsed(false);
		userService.save(foundUser);
		MailingModel mailModel = new MailingModel();
		mailModel.setTo(email);
		mailModel.setSubject("Confirmation on request of " + documentType);
		mailModel.setBody("Hi!"
				+ "This is from Legalchain you have requested to initate transfer of " + documentType + " the OTP is " + otp + ". If its not requested by you kindly report in legalchain site.");
		prophetService.sendMail(mailModel);
	}

	@Override
	public ResponseModel<Object> requestDocument(DocumentRequestModel doc) {
		log.error("URL: {}", parivahanConfig.getInitiate2fUrl());
		ResponseEntity<ResponseModel> response = restTemplate.postForEntity(parivahanConfig.getInitiate2fUrl(), doc, ResponseModel.class);
		return response.getBody();
	}

	@Override
	public LicenseInfo transferDrivingLicense(RequestLicenseModel req) {
		log.error("URL: {}", parivahanConfig.getLicenseRequestUrl());
		ResponseEntity<LicenseInfo> response = restTemplate.postForEntity(parivahanConfig.getLicenseRequestUrl(), req, LicenseInfo.class);
		return response.getBody();

	}

}
