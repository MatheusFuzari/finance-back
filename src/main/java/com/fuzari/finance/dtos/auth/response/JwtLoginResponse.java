package com.fuzari.finance.dtos.auth.response;

import lombok.Builder;

@Builder
public record JwtLoginResponse(
    String status,
    String token) { }
