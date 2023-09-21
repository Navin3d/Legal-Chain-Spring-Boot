package gmc.project.blockchain.legalchain.vaultservice.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ResponseModel<T> implements Serializable {
	
	private static final long serialVersionUID = -1441160979862166583L;
	
	private String message;
	
	private Integer status;
	
	private String error;
	
	private T data;
	
	private List<T> datas = new ArrayList<>();

}
