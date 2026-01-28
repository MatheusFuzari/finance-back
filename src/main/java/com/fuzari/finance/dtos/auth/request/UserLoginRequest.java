package com.fuzari.finance.dtos.auth.request;

/*
  TODO
  1. Add validations for all fields
  2. Add SpringDoc API  @Schema for all fields
 */
public record UserLoginRequest(
    String email,
    String password) {}

