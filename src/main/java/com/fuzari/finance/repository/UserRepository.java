package com.fuzari.finance.repository;

import com.fuzari.finance.domain.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, UUID> {

  Optional<User> findByEmail(String email);

  Optional<User> findByIdAndEmailNot(UUID id, String email);
}
