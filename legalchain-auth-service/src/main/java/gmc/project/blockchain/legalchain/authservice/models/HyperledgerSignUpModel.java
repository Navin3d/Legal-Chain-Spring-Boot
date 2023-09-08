package gmc.project.blockchain.legalchain.authservice.models;

import java.io.Serializable;

import lombok.Data;

@Data
public class HyperledgerSignUpModel implements Serializable {
	
	private static final long serialVersionUID = -5964413817695327361L;
	
	private String orgMspId;
	
	private String walletAdminUserId;
	
	private String userId;
	
	private String affiliation;

	public HyperledgerSignUpModel(String walletAdminUserId, String userId) {
		super();
		this.walletAdminUserId = walletAdminUserId;
		this.userId = userId;
	}

}
