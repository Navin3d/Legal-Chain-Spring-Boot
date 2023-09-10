package gmc.project.blockchain.legalchain.vaultservice.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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
	private Set<RecordEntity> record = new HashSet<>();
	
	@DBRef
	private Set<UserEntity> sharedWith = new HashSet<>();
	
	@Field(name = "shared_at")
	private LocalDate sharedAt;

	@Field(name = "share_until")
	private LocalDate shareUntil;
	
}
