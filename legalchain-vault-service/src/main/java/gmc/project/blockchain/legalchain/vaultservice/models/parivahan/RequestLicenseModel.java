package gmc.project.blockchain.legalchain.vaultservice.models.parivahan;

import java.io.Serializable;

import lombok.Data;

@Data
public class RequestLicenseModel implements Serializable {
	
	private static final long serialVersionUID = -3540881165783243351L;
	
	private String email;
	
	private String otp;

	public RequestLicenseModel(String email, String otp) {
		super();
		this.email = email;
		this.otp = otp;
	}

}
