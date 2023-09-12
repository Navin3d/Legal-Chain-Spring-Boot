package gmc.project.blockchain.legalchain.vaultservice.models;

import java.io.Serializable;

import lombok.Data;

@Data
public class DocumentModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;

	private String tittle;

	private String description;

	private String hash;

	private String timeStamp;

}
