package com.fuzari.finance.controller;

import com.fuzari.finance.dtos.user.request.UserPostRequest;
import com.fuzari.finance.dtos.user.response.UserGetResponse;
import com.fuzari.finance.mapper.UserMapper;
import com.fuzari.finance.services.UserService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService service;

  private final UserMapper MAPPER;

  @GetMapping
  private ResponseEntity<List<UserGetResponse>> getUsers() {
    var response = MAPPER.userListToUserGetResponseList(service.getAll());

    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @PostMapping
  private ResponseEntity<UserGetResponse> getUsers(@RequestBody @Valid UserPostRequest body) {
    var user_to_save = MAPPER.userPostRequestToUser(body);
    var response = MAPPER.userToUserGetResponse(service.create(user_to_save));

    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

}
