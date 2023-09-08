package gmc.project.blockchain.legalchain.authservice.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Document(collection = "sharings")
@Data
public class SharingEntity implements Serializable {

	private static final long serialVersionUID = 2498862799827408547L;
	
	@Id
	private String id;
	
	@DBRef
	private RecordEntity record;
	
	@DBRef
	private UserEntity sharedWith;
	
	@Field(name = "shared_at")
	private LocalDateTime sharedAt;

	@Field(name = "share_until")
	private LocalDateTime shareUntil;
	
}
