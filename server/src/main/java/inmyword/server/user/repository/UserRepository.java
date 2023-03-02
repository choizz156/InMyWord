package inmyword.server.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import inmyword.server.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByDisplayName(String displayName);

	Optional<User> findByEmail(String email);
}
