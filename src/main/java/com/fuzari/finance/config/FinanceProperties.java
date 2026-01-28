package com.fuzari.finance.config;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "finance")
public record FinanceProperties (RSAPublicKey publicKey, RSAPrivateKey privateKey) {}