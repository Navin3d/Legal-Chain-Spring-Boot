package gmc.project.blockchain.legalchain.vaultservice.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import gmc.project.blockchain.legalchain.vaultservice.models.MailingModel;

@FeignClient("Legalchain-Prophet-Service")
public interface ProphetServiceFeignClient {
	
	@PostMapping(path = "/mail")
	public ResponseEntity<String> sendMail(MailingModel mailingModel);

}
