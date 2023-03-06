package inmyword.server.config.security;

import inmyword.server.user.entity.User;
import inmyword.server.user.repository.UserRepository;
import java.util.Collection;
import java.util.Optional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class PrincipalDetails implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(
        String username) throws UsernameNotFoundException {

        Optional<User> optionalUser = userRepository.findByEmail(username);
        log.info("{}", optionalUser);
        User userEntity = optionalUser.orElseThrow(
            () -> new UsernameNotFoundException("찾을 수 없는 이메일입니다."));
        log.info("{}", userEntity);
        return new UserDetailEntity(userEntity);
    }

    @Getter
    public static class UserDetailEntity implements UserDetails {

        private  User user;

        UserDetailEntity(User userEntity) {
            this.user = userEntity;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return AuthorityUtils.createAuthorityList(user.getUserRole().name());
        }

        @Override
        public String getPassword() {
            return user.getPassword();
        }

        @Override
        public String getUsername() {
            return user.getEmail();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }

}
