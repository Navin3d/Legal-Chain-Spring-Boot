package gmc.project.blockchain.legalchain.authservice.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "auth")
public class AuthConfig {
	private String auth2FUrl;
	private String authUrl;
	private String issuer;
	private String jwtSecret;
	private long expeiry;
	private String refreshToken;
}
