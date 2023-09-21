package gmc.project.blockchain.legalchain.vaultservice.models.parivahan;

import java.io.Serializable;

import lombok.Data;

@Data
public class DocumentRequestModel implements Serializable {
	
	private static final long serialVersionUID = 6006498347762467262L;
	
	private String type;
	
	private String ref;
	
	private String dob;

	public DocumentRequestModel(String type, String ref, String dob) {
		super();
		this.type = type;
		this.ref = ref;
		this.dob = dob;
	}

}
