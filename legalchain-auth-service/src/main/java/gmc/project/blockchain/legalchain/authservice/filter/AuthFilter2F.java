package gmc.project.blockchain.legalchain.authservice.filter;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import gmc.project.blockchain.legalchain.authservice.models.LoginModel;
import gmc.project.blockchain.legalchain.authservice.models.UserModel;
import gmc.project.blockchain.legalchain.authservice.services.AuthService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthFilter2F extends UsernamePasswordAuthenticationFilter {
	
	private final AuthService authService;
	
	public AuthFilter2F(AuthService authService, AuthenticationManager authManager) {
		super.setAuthenticationManager(authManager);
		this.authService = authService;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
		try {
			LoginModel creds = new ObjectMapper().readValue(request.getInputStream(), LoginModel.class);
			UserModel foundUser = authService.findOneUser(creds.getUsername());
			if(foundUser.getOtp().equals(creds.getOtp()) && foundUser.getIsOTPUsed().equals(false))
				return getAuthenticationManager().authenticate(
						new UsernamePasswordAuthenticationToken(creds.getUsername(), creds.getPassword())
					);
			else
				return getAuthenticationManager().authenticate(
						new UsernamePasswordAuthenticationToken(creds.getUsername(), "")
					);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) {
		String subject = "OK";
		response.setHeader("Authorization", subject);
	}

}
