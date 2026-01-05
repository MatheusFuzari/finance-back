package com.fuzari.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  // Request body validation
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<DefaultErrorMessage> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    var default_message = e.getBindingResult()
      .getAllErrors()
        .stream()
        .map(DefaultMessageSourceResolvable::getDefaultMessage)
        .filter(Objects::nonNull)
        .sorted()
        .collect(Collectors.joining(", "));

    var error = new DefaultErrorMessage(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), default_message, OffsetDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<DefaultErrorMessage> handleNotFoundException(NotFoundException e) {
    var error = new DefaultErrorMessage(
        HttpStatus.NOT_FOUND.value(),
        HttpStatus.NOT_FOUND.getReasonPhrase(),
        e.getMessage(),
        OffsetDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)
    );

    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(UserAlreadyHaveWageException.class)
  public ResponseEntity<DefaultErrorMessage> handleUserAlreadyHaveWageException(UserAlreadyHaveWageException e) {
    var error = new DefaultErrorMessage(
        HttpStatus.CONFLICT.value(),
        HttpStatus.CONFLICT.getReasonPhrase(),
        e.getMessage(),
        OffsetDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)
    );

    return new ResponseEntity<>(error, HttpStatus.CONFLICT);
  }

  @ExceptionHandler(EmailAlreadyInUseException.class)
  public ResponseEntity<DefaultErrorMessage> handleEmailAlreadyInUseException(EmailAlreadyInUseException e) {
    var error = new DefaultErrorMessage(
        HttpStatus.CONFLICT.value(),
        HttpStatus.CONFLICT.getReasonPhrase(),
        e.getMessage(),
        OffsetDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)
    );

    return new ResponseEntity<>(error, HttpStatus.CONFLICT);
  }

  @ExceptionHandler(SheetAlreadyHaveCellInLocationException.class)
  public ResponseEntity<DefaultErrorMessage> handleSheetAlreadyHaveCellInLocationException(SheetAlreadyHaveCellInLocationException e) {
    var error = new DefaultErrorMessage(
        HttpStatus.CONFLICT.value(),
        HttpStatus.CONFLICT.getReasonPhrase(),
        e.getMessage(),
        OffsetDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)
    );

    return new ResponseEntity<>(error, HttpStatus.CONFLICT);
  }
}
