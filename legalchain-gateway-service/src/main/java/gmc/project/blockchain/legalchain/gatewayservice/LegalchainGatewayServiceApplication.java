package gmc.project.blockchain.legalchain.gatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class LegalchainGatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LegalchainGatewayServiceApplication.class, args);
	}

}
