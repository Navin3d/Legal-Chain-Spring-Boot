package gmc.project.blockchain.legalchain.vaultservice.models;

import java.io.Serializable;

import lombok.Data;

@Data
public class InsertRecordModel implements Serializable {
	
	private static final long serialVersionUID = 2978477348298712942L;
	
	private String id;
	
	private String insertedAt;
	
	private String ownedBy;

}
