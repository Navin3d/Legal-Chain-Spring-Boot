package gmc.project.blockchain.legalchain.vaultservice.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import gmc.project.blockchain.legalchain.vaultservice.models.BlockchainResponse;

@FeignClient("legalchain-blockchain-service")
public interface BlockchainServiceFeignClient {
	
	@GetMapping(path = "/legal/contract/get/{userId}/many/{documentIds}")
	public BlockchainResponse getDocuments(@PathVariable String userId, @PathVariable String documentIds);

}
