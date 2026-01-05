package com.fuzari.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class SheetAlreadyHaveCellInLocationException extends ResponseStatusException {

  public SheetAlreadyHaveCellInLocationException(String message) { super(HttpStatus.CONFLICT, message);}
}
