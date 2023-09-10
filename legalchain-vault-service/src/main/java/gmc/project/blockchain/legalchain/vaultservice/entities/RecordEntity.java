package gmc.project.blockchain.legalchain.vaultservice.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(exclude = { "ownedBy" })
@Document(collection = "records")
@Data
public class RecordEntity implements Serializable {
	
	private static final long serialVersionUID = 1647338966264664241L;

	@Id
	private String id;
	
	@Field(name = "inserted_at")
	private LocalDateTime insertedAt;
	
	@DBRef
	private UserEntity ownedBy;
	
}
