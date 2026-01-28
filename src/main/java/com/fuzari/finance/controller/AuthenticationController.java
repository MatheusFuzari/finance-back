package com.fuzari.finance.controller;

import com.fuzari.finance.dtos.auth.request.UserLoginRequest;
import com.fuzari.finance.dtos.auth.request.UserRegisterRequest;
import com.fuzari.finance.dtos.auth.response.JwtLoginResponse;
import com.fuzari.finance.dtos.user.response.UserGetResponse;
import com.fuzari.finance.mapper.UserMapper;
import com.fuzari.finance.services.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties.Http;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final UserMapper MAPPER;
  private final AuthenticationService authenticationService;

  @PostMapping("/signup")
  public ResponseEntity<UserGetResponse> registerUser(@RequestBody @Valid UserRegisterRequest userRequest) {
    var userToSave = MAPPER.userPostRequestToUser(userRequest);
    var createdUser = authenticationService.createUser(userToSave);

    return ResponseEntity.status(HttpStatus.CREATED).body(MAPPER.userToUserGetResponse(createdUser));
  }

  @PostMapping("/login")
  public ResponseEntity<JwtLoginResponse> loginUser(@RequestBody @Valid UserLoginRequest userLogin) {
    System.out.println("LOGIN ENDPOINT");
    var userToLogin = MAPPER.userLoginRequestToUser(userLogin);
    var jwt = authenticationService.loginUser(userToLogin);
    System.out.println("Token: " + jwt);
    return ResponseEntity.status(HttpStatus.OK).body(JwtLoginResponse.builder().token(jwt).status(String.valueOf(HttpStatus.OK.value())).build());
  }

}
