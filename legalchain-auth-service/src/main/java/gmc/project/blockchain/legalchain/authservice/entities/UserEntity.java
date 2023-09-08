package gmc.project.blockchain.legalchain.authservice.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Document(collection = "users")
@Data
public class UserEntity implements Serializable {
	
	private static final long serialVersionUID = -7006435200306973690L;
	
	@Id
	private String id;

	@Field(name = "first_name")
	private String firstName;
	
	@Field(name = "last_name")
	private String lastName;
	
	@Field(name = "username")
	private String username;
	
	@Field(name = "mobile_number")
	private String mobileNumber;
	
	@Field(name = "email")
	private String email;
	
	@Field(name = "password")
	private String encryptedPassword;
	
	@Field(name = "aadhar_number")
	private String aadharNumber;
	
	@Field(name = "is_legal_user")
	private Boolean isLegalUser;
	
	@DBRef
	private Set<RecordEntity> recordsOwned = new HashSet<>();
	
	@DBRef
	private Set<RecordEntity> recordsShared = new HashSet<>();
	
}
