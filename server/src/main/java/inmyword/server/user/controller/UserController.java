package inmyword.server.user.controller;

import inmyword.server.user.dto.JoinDto;
import inmyword.server.user.dto.UpdateDto;
import inmyword.server.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String signUp(@RequestBody @Valid JoinDto joinDto) {
        userService.signUp(joinDto);
        log.warn("회원가입 완료");
        return "회원 가입이 완료되었습니다.";
    }

    @PatchMapping("/update/display-name")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String updateDisplayName(@RequestBody @Valid UpdateDto updateDto) {
        userService.updateDisplayName(updateDto.displayName());
        return updateDto.displayName();
    }

    @PatchMapping("/update/password")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String updatePwd(@RequestBody @Validated UpdateDto updateDto) {
        log.warn(updateDto.password());
        userService.updatePwd(updateDto.password());
        return "비밀번호가 수정되었습니다.";
    }
}
