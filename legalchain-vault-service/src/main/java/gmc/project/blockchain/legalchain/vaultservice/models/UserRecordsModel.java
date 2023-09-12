package gmc.project.blockchain.legalchain.vaultservice.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class UserRecordsModel implements Serializable {
	
	private static final long serialVersionUID = -6449487102691548340L;
	
	private List<DocumentModel> ownedRecords = new ArrayList<>();
	
	private List<DocumentModel> sharedRecords = new ArrayList<>();

}
