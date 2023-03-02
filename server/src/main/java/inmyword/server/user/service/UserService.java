package inmyword.server.user.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import inmyword.server.user.dto.JoinDto;
import inmyword.server.user.entity.User;
import inmyword.server.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class UserService {
	private final UserRepository userRepository;

	@Transactional
	public void signUp(JoinDto joinDto) {
		isExistDisplayName(joinDto.displayName());
		isExistDisplayName(joinDto.email());
		User user = new User(joinDto.displayName(), joinDto.password(), joinDto.email());
		//TODO passwordEncoder
		user.savePwd(joinDto.password());
		userRepository.save(user);
	}
	private void isExistDisplayName(String displayName) {
		Optional<User> optionalUser = userRepository.findByDisplayName(displayName);
		optionalUser.ifPresent((user) -> new RuntimeException("이미 존재하는 닉네임입니다."));
	}
	private void  isExistEmail(String email) {
		Optional<User> optionalUser = userRepository.findByEmail(email);
		optionalUser.ifPresent((user) -> new RuntimeException("이미 존재하는 이메일입니다."));
	}
}
