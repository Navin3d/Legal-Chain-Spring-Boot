package gmc.project.blockchain.legalchain.vaultservice.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class SharringModel implements Serializable {
	
	private static final long serialVersionUID = 5996161362351475655L;
	
	private List<String> recordIds = new ArrayList<>();
	
	private List<String> userIds = new ArrayList<>();
	
	private String shareUntil;

}
