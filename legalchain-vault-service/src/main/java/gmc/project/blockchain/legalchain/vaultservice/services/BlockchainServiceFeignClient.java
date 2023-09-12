package gmc.project.blockchain.legalchain.vaultservice.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import gmc.project.blockchain.legalchain.vaultservice.models.BlockchainResponse;
import gmc.project.blockchain.legalchain.vaultservice.models.GetDocumentsModel;

@FeignClient("legalchain-blockchain-service")
public interface BlockchainServiceFeignClient {
	
	@PostMapping(path = "/legal/contract/get/many")
	public BlockchainResponse getDocuments(@RequestBody GetDocumentsModel getDocumentsModel);

}
