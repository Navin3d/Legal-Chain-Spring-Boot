package gmc.project.blockchain.legalchain.vaultservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@ConfigurationProperties(prefix = "parivahan")
@Component
@Data
public class ParivahanServerConfig {
	
	private String host;
	
	private String initiate2fUrl;
	
	private String licenseRequestUrl;

}
