package com.fuzari.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserAlreadyHaveWageException extends ResponseStatusException {

  public UserAlreadyHaveWageException(String message) {
    super(HttpStatus.CONFLICT, message);
  }
}
