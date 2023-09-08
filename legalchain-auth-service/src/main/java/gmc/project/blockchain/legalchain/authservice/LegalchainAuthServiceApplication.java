package gmc.project.blockchain.legalchain.authservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableMongoRepositories
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class LegalchainAuthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LegalchainAuthServiceApplication.class, args);
	}
	
	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
