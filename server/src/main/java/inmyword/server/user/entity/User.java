package inmyword.server.user.entity;

import inmyword.server.audit.BaseEntity;
import inmyword.server.config.security.PrincipalDetails.UserDetailEntity;
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
import org.springframework.security.core.context.SecurityContextHolder;

@Entity
@Getter
@Table(name = "users")
@NoArgsConstructor
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String displayName;

    @NotNull
    private String password;

    @NotNull
    private String email;

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus = UserStatus.ACTIVE;

    @Enumerated(EnumType.STRING)
    private UserRole userRole = UserRole.USER;

    @Builder
    public User(final String displayName, final String password, final String email) {
        this.displayName = displayName;
        this.password = password;
        this.email = email;
    }

    public void savePwd(final String password) {
        this.password = password;
    }

    public static User loginUser() {
        UserDetailEntity userEntity =
            (UserDetailEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userEntity.getUser();
    }

    public void updateDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void updatePwd(String encodedPwd) {
        this.password = encodedPwd;
    }
}
