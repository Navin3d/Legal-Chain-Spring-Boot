package gmc.project.blockchain.legalchain.authservice.models;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserModel implements Serializable {

	private static final long serialVersionUID = 7021086701864351662L;

	private String id;

	private String firstName;

	private String lastName;

	private String aadharNumber;

	private String username;

	private String mobileNumber;

	private String email;

	private String password;
	
	private String encryptedPassword;

	private Boolean isLegalUser;

}
