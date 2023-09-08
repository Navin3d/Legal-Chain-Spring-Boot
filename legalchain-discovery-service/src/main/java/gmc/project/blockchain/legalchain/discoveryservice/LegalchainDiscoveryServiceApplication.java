package gmc.project.blockchain.legalchain.discoveryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class LegalchainDiscoveryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LegalchainDiscoveryServiceApplication.class, args);
	}

}
