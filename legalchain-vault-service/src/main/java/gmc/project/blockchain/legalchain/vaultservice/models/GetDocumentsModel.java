package gmc.project.blockchain.legalchain.vaultservice.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class GetDocumentsModel implements Serializable {
	
	public GetDocumentsModel(String userId) {
		this.userId = userId;
	}
	
	private static final long serialVersionUID = 458866633628502444L;
	
	private String userId;
	
	private List<String> documentIds = new ArrayList<>();

}
