package inmyword.server.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record JoinDto(@NotBlank(message = "닉네임을 적어주세요!")
					  String displayName,
					  @Email(message = "올바른 이메일 형식이 아닙니다.")
					  @NotBlank(message = "이메일을 적어주세요!")
					  String email,
					  @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[!@#$%^+=-])(?=.*[0-9]).{8,25}$",
						  message = "비밀번호는 숫자+영문자+특수문자 조합으로 8자리 이상 입력해 주세요!")
					  String password) {
}
