package io.shifu.doskiad.repository;

import io.shifu.doskiad.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByConfirmationToken(String token);

    Optional<User> findByResetToken(String token);
}
