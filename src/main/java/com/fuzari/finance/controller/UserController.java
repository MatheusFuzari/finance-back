package com.fuzari.finance.controller;

import com.fuzari.finance.dtos.auth.request.UserRegisterRequest;
import com.fuzari.finance.exceptions.DefaultErrorMessage;
import com.fuzari.finance.dtos.user.response.UserGetResponse;
import com.fuzari.finance.mapper.UserMapper;
import com.fuzari.finance.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "User API", description = "User related endpoints")
public class UserController {

  private final UserService service;

  private final UserMapper MAPPER;

  @GetMapping
  @Operation(
    summary = "Get all Users",
    responses = {
        @ApiResponse(
          description = "List all Users",
          responseCode = "200",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = UserGetResponse.class)))
        )
    }
  )
  private ResponseEntity<List<UserGetResponse>> getUsers() {
    var response = MAPPER.userListToUserGetResponseList(service.getAll());

    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @PostMapping
  @Operation(
      summary = "Create an User",
      responses = {
          @ApiResponse(
            description = "Created User",
            responseCode = "201",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = UserGetResponse.class)))
          ),
          @ApiResponse(
              description = "JSON body validation error",
              responseCode = "400",
              content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = DefaultErrorMessage.class)))
          ),
          @ApiResponse(
              description = "User e-mail already taken",
              responseCode = "409",
              content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = DefaultErrorMessage.class)))
          )
      }
  )
  private ResponseEntity<UserGetResponse> createUser(@RequestBody @Valid UserRegisterRequest body) {
    var user_to_save = MAPPER.userPostRequestToUser(body);
    var response = MAPPER.userToUserGetResponse(service.create(user_to_save));

    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

}
