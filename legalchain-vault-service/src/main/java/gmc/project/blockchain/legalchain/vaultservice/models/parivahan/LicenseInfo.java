package gmc.project.blockchain.legalchain.vaultservice.models.parivahan;

import java.io.Serializable;

import lombok.Data;

@Data
public class LicenseInfo implements Serializable {

	private static final long serialVersionUID = 2099996659734229777L;
	
	private String name;
	private String dateOfBirth;
	private String licenseNumber;
	private String licenseHash;

}
