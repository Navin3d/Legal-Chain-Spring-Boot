package gmc.project.blockchain.legalchain.vaultservice.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class SecurityConfig {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(authReq -> authReq.requestMatchers("/**").permitAll().anyRequest().authenticated())
			.headers(headers -> headers.frameOptions(opt -> opt.disable()).disable());
		return http.build();
	}

}
