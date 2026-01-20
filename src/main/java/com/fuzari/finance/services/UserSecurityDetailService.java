package com.fuzari.finance.services;

import com.fuzari.finance.domain.User;
import com.fuzari.finance.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserSecurityDetailService implements UserDetailsService {

  private final UserRepository userRepository;

  @Transactional(readOnly = true)
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


    if(username != null) {
      User user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
      List<GrantedAuthority> authorities = user.getRoles().stream()
          .map(SimpleGrantedAuthority::new).collect(Collectors.toList());

      return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }
    throw new UsernameNotFoundException("User not found");
  }
}
