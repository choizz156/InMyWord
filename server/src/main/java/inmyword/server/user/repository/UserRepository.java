package inmyword.server.user.repository;

import inmyword.server.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByDisplayName(String displayName);

    Optional<User> findByEmail(String email);
}
