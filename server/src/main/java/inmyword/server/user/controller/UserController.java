package inmyword.server.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import inmyword.server.user.dto.JoinDto;
import inmyword.server.user.service.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;

	@PostMapping
	public ResponseEntity Join(@RequestBody @Validated JoinDto joinDto) {
		userService.signUp(joinDto);
		return new ResponseEntity<String>("회원가입이 완료되었습니다.", HttpStatus.OK);
	}
}
