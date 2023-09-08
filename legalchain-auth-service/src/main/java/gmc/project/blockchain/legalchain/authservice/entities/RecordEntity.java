package gmc.project.blockchain.legalchain.authservice.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Document(collection = "records")
@Data
public class RecordEntity implements Serializable {
	
	private static final long serialVersionUID = 1647338966264664241L;

	@Id
	private String id;
	
	@Field(name = "tittle")
	private String tittle;
	
	@Field(name = "description")
	private String description;
	
	@Field(name = "file_hash")
	private String fileHash;
	
	@Field(name = "uploaded_at")
	private LocalDateTime uploadedAt;
	
	@DBRef
	private UserEntity ownedBy;
	
}
