package gmc.project.blockchain.legalchain.authservice.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import gmc.project.blockchain.legalchain.authservice.models.HyperledgerSignUpModel;

@FeignClient("legalchain-blockchain-service")
public interface BlockchainServiceFeignClient {
	
	@GetMapping(path = "/legal/wallet/user")
	public void createLegalWalletUser(HyperledgerSignUpModel user);
	
	@GetMapping(path = "/civil/wallet/user")
	public void createCivilWalletUser(HyperledgerSignUpModel user);

}
