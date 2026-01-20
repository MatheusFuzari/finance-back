package com.fuzari.finance.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EmailAlreadyInUseException extends ResponseStatusException {

  public EmailAlreadyInUseException(String message) {
    super(HttpStatus.CONFLICT, message);
  }
}
