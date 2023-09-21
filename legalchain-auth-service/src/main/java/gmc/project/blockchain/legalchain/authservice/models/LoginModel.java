package gmc.project.blockchain.legalchain.authservice.models;

import java.io.Serializable;

import lombok.Data;

@Data
public class LoginModel implements Serializable {
	
	private static final long serialVersionUID = -8868477007217019796L;
	
	private String username;
	
	private String password;
	
	private String otp;

}
