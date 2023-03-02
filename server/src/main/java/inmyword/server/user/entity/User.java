package inmyword.server.user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "users")
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String displayName;

	@NotNull
	private String password;

	@NotNull
	private  String email;

	@Enumerated(EnumType.STRING)
	private UserStatus userState = UserStatus.ACTIVE;

	@Enumerated(EnumType.STRING)
	private UserRole role = UserRole.USER;

	@Builder
	public User(final String displayName, final String password, final String email) {
		this.displayName = displayName;
		this.password = password;
		this.email = email;
	}

	public void savePwd(final String password) {
		this.password = password;
	}


}
