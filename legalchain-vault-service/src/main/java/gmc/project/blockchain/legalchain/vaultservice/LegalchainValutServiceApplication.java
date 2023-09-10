package gmc.project.blockchain.legalchain.vaultservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableFeignClients
@EnableMongoRepositories
@EnableDiscoveryClient
@SpringBootApplication
public class LegalchainValutServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LegalchainValutServiceApplication.class, args);
	}

}
