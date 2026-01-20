package com.fuzari.finance.config;

import com.fuzari.finance.services.UserSecurityDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserProviderDetailsManager implements AuthenticationProvider {

  private final UserSecurityDetailService userSecurityDetailService;
  private final PasswordEncoder passwordEncoder;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String username = authentication.getName();
    String password = authentication.getCredentials().toString();

    if(passwordEncoder.matches(password, userSecurityDetailService.loadUserByUsername(username).getPassword())) {
      return new UsernamePasswordAuthenticationToken(username, password, userSecurityDetailService.loadUserByUsername(username).getAuthorities());
    }

    throw new BadCredentialsException("Bad Credentials");
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
  }
}
