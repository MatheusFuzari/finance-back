package com.fuzari.finance.services;

import com.fuzari.finance.config.FinanceProperties;
import com.fuzari.finance.domain.User;
import com.fuzari.finance.exceptions.EmailAlreadyInUseException;
import com.fuzari.finance.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtEncoder encoder;
  private final AuthenticationManager authenticationManager;


  public User createUser(User userToCreate) {
    Optional<User> userAvailable = userRepository.findByEmail(userToCreate.getEmail());

    if(userAvailable.isPresent()) {
      throw new EmailAlreadyInUseException("Email %s is already taken".formatted(userToCreate.getEmail()));
    }

    String password = userToCreate.getPassword();
    userToCreate.setPassword(passwordEncoder.encode(password));

    return userRepository.save(userToCreate);
  }

  public String loginUser(User userToLogin) {
    UsernamePasswordAuthenticationToken user = UsernamePasswordAuthenticationToken.unauthenticated(userToLogin.getEmail(), userToLogin.getPassword());
    Authentication authentication = authenticationManager.authenticate(user);
    System.out.println(authentication.isAuthenticated());
    if (authentication.isAuthenticated()) {
      var jwt = this.generateToken(authentication);
      System.out.println("Token: " + jwt);
      return jwt;
    }
    throw new BadCredentialsException(String.format("Bad credentials for user %s",authentication.getName()));
  }

  private String generateToken(Authentication authentication) {
    Instant now = Instant.now();
    long expiry = 3600L;

    String scopes = authentication.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.joining(","));

    var claims = JwtClaimsSet.builder()
        .issuer("finance-security")
        .issuedAt(now)
        .expiresAt(now.plusSeconds(expiry))
        .subject(authentication.getName())
        .claim("scope", scopes)
        .build();

    return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
  }
}
