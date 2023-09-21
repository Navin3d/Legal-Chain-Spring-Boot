package gmc.project.blockchain.legalchain.vaultservice.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;
import lombok.EqualsAndHashCode;
@EqualsAndHashCode(exclude = { "recordsOwned", "recordsShared" })
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
	
	@Indexed(unique = true, background = true)
	@Field(name = "aadhar_number")
	private String aadharNumber;
	
	@Indexed(unique = true, background = true)
	@Field(name = "username")
	private String username;
	
	@Indexed(unique = true, background = true)
	@Field(name = "mobile_number")
	private String mobileNumber;
	
	@Indexed(unique = true, background = true)
	@Field(name = "email")
	private String email;
	
	@Field(name = "password")
	private String encryptedPassword;
	
	@Field(name = "is_legal_user")
	private Boolean isLegalUser;
	
	@Field(name = "otp")
	private String otp;
	
	@Field(name = "is_otp_used")
	private Boolean isOTPUsed;
	
	@DBRef
	private Set<RecordEntity> recordsOwned = new HashSet<>();
	
	@DBRef
	private Set<RecordEntity> recordsShared = new HashSet<>();
	
}
