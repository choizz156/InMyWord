package inmyword.server.user.service;

import inmyword.server.user.dto.JoinDto;
import inmyword.server.user.entity.User;
import inmyword.server.user.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signUp(JoinDto joinDto) {
        isExistDisplayName(joinDto.displayName());
        isExistEmail(joinDto.email());

        User user = new User(joinDto.displayName(), joinDto.password(), joinDto.email());
        String password = passwordEncoder.encode(joinDto.password());

        user.savePwd(password);
        userRepository.save(user);
    }

    @Transactional
    public void updateDisplayName(String displayName) {
        User userEntity = getUserEntity();
        userEntity.updateDisplayName(displayName);
    }

    @Transactional
    public void updatePwd(String password) {
        User userEntity = getUserEntity();
        String encodedPwd = passwordEncoder.encode(password);
        userEntity.updatePwd(encodedPwd);
    }


    private User getUserEntity() {
        User user = User.loginUser();
        Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());
        return optionalUser.get();
    }

    private void isExistDisplayName(String displayName) {
        Optional<User> optionalUser = userRepository.findByDisplayName(displayName);
        optionalUser.ifPresent((user) -> new RuntimeException("이미 존재하는 닉네임입니다."));
    }

    private void isExistEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        optionalUser.ifPresent((user) -> new RuntimeException("이미 존재하는 이메일입니다."));
    }

}
