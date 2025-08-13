package com.fuzari.exceptions;

public record DefaultErrorMessage(
    int status,
    String error,
    String message,
    String timestamp
) { }
