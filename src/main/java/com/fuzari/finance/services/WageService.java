package com.fuzari.finance.services;

import com.fuzari.finance.exceptions.NotFoundException;
import com.fuzari.finance.exceptions.UserAlreadyHaveWageException;
import com.fuzari.finance.domain.Wage;
import com.fuzari.finance.repository.WageRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WageService {

  private final WageRepository repository;

  public Wage getUserWage(UUID user_id) {
    return this.repository.findWageByUserId(user_id).orElseThrow(
        () -> new NotFoundException("No wage registered for user with id: %s".formatted(user_id.toString()))
    );
  }

  public Wage createUserWage(Wage wage_to_save) {
    assertThatUserNotHaveWage(wage_to_save.getUser().getId());
    var response = this.repository.save(wage_to_save);

    return response;
  }

  public void updateUserWage(Wage wage_to_update) {
    this.assertThatUserHasWage(wage_to_update.getUser().getId());
    this.repository.save(wage_to_update);
  }

  private void assertThatUserNotHaveWage(UUID user_id) {
    this.repository.findWageByUserId(user_id).ifPresent(this::throwUserAlreadyHaveWage);
  }

  private void assertThatUserHasWage(UUID user_id) {
    this.getUserWage(user_id);
  }

  private void throwUserAlreadyHaveWage(Wage wage) {
    throw new UserAlreadyHaveWageException("User already have an Wage registered");
  }
}
