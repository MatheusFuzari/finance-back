package com.fuzari.finance.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "finance")
public record FinanceProperties (String securityAllowedOrigin, String jwtSecretKeyValue, String jwtHeader) {}