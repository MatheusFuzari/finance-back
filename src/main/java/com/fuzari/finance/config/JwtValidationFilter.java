package com.fuzari.finance.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
public class JwtValidationFilter extends OncePerRequestFilter {

  private final FinanceProperties financeProperties;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    String jwt = request.getHeader(financeProperties.jwtHeader());

    if(jwt != null) {
      try {
        String secret = financeProperties.jwtSecretKeyValue();
        SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

        if(secretKey != null) {
          Claims claims = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(jwt).getPayload();
          String email = String.valueOf(claims.get("email"));
          String authorities = String.valueOf(claims.get("authorities"));
          Authentication authentication = new UsernamePasswordAuthenticationToken(
              email, null, AuthorityUtils.commaSeparatedStringToAuthorityList(authorities)
          );
          SecurityContextHolder.getContext().setAuthentication(authentication);

        }
      } catch (Exception e) {
        throw new BadCredentialsException("Token invalid");
      }
    }
  }

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
    return request.getServletPath().equals("/api/auth/login");
  }
}
