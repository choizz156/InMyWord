package inmyword.server.user.dto;

import jakarta.validation.constraints.Pattern;

public record UpdateDto(
    String displayName,

    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[!@#$%^+=-])(?=.*[0-9]).{8,25}$",
        message = "비밀번호는 숫자+영문자+특수문자 조합으로 8자리 이상 입력해 주세요!")
    String password
) {

}
