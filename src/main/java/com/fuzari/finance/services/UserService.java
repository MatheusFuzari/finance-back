package com.fuzari.finance.services;

import com.fuzari.exceptions.EmailAlreadyInUseException;
import com.fuzari.finance.domain.User;
import com.fuzari.finance.repository.UserRepository;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository repository;

  public List<User> getAll() {
    return repository.findAll();
  }

  public User create(User user_to_create) {
    assetEmailIsAvailable(user_to_create.getEmail());
    return repository.save(user_to_create);
  }

  public void delete(UUID user_id) {
    repository.deleteById(user_id);
  }

  public void assetEmailIsAvailable(String email) {
    if (repository.findByEmail(email).isPresent()) {
      throw new EmailAlreadyInUseException("The e-mail %s is already in use, please, chose another one".formatted(email));
    }
  }
}
