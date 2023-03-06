package inmyword.server.user.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
public class LoginResponseDto {

    private String email;
    private String displayName;
    private LocalDateTime createAt;

    @Builder
    public LoginResponseDto(String email, String displayName, LocalDateTime createAt) {
        this.email = email;
        this.displayName = displayName;
        this.createAt = createAt;
    }
}
