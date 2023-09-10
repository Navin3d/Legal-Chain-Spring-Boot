package gmc.project.blockchain.legalchain.vaultservice.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Data;

@Data
public class BlockchainDocModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonAlias({ "DocumentId" })
	private String documentId;
	
	@JsonAlias({ "UserId" })
	private String userId;
	
	@JsonAlias({ "DocumentData" })
	private DocumentModel documentData;

}
