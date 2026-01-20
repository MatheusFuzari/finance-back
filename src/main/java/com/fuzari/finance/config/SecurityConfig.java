package com.fuzari.finance.config;

import com.fuzari.finance.services.UserSecurityDetailService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
@Slf4j
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
  private static final String[] WHITE_LIST = {"/swagger-ui.html", "/v3/**", "/swagger-ui/**", "/csrf","/api/auth/**"};
  private final FinanceProperties financeProperties;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
        .csrf(AbstractHttpConfigurer::disable)
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(auth ->
          auth.anyRequest().authenticated()
          .requestMatchers(WHITE_LIST).permitAll()
        )
        .addFilterBefore(new JwtValidationFilter(financeProperties), BasicAuthenticationFilter.class)
        .cors(cors->cors.configurationSource(
                new CorsConfigurationSource() {
                  @Override
                  public CorsConfiguration getCorsConfiguration(
                      HttpServletRequest request) {
                    CorsConfiguration cors = new CorsConfiguration();
                    cors.addAllowedOrigin(financeProperties.securityAllowedOrigin());
                    cors.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE","PATCH"));
                    cors.setAllowCredentials(true);
                    cors.setAllowedHeaders(List.of("*"));
                    cors.setExposedHeaders(List.of("Authorization"));
                    cors.setMaxAge(3600L);
                    return cors;
                  }
                }
            )
        )
        .logout(logout ->
            logout.deleteCookies("CSRF-TOKEN")
                .logoutUrl("/api/auth/logout")
                .invalidateHttpSession(true).permitAll()
                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK))
        )
        .formLogin(AbstractHttpConfigurer::disable)
        .httpBasic(AbstractHttpConfigurer::disable)
        .build();
  }

  /*
    Bean responsible for if the password has been exposed
    in a known data breach.
   */
  @Bean
  public CompromisedPasswordChecker compromisedPasswordChecker() {
    return new HaveIBeenPwnedRestApiPasswordChecker();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(UserSecurityDetailService userDetailsService, PasswordEncoder passwordEncoder) {
    UserProviderDetailsManager userProviderDetailsManager = new UserProviderDetailsManager(userDetailsService, passwordEncoder);
    return new ProviderManager(userProviderDetailsManager);
  }
}
