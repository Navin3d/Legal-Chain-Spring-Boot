package gmc.project.blockchain.legalchain.vaultservice.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class BlockchainResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private String message;

	private Integer status;

	private List<BlockchainDocModel> data = new ArrayList<>();

}
