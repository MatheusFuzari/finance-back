package com.fuzari.exceptions;

import io.swagger.v3.oas.annotations.media.Schema;

public record DefaultErrorMessage(
    @Schema(description = "Error status code", example = "400")
    int status,
    @Schema(description = "Error verbose name", example = "Bad Request")
    String error,
    @Schema(description = "Error message", example = "You've made a mistake")
    String message,
    @Schema(description = "Error occur timestamp", example = "2025-08-26T22:07:00")
    String timestamp
) { }
