package com.fuzari.finance.controller;

import com.fuzari.finance.dtos.wage.request.WagePostRequest;
import com.fuzari.finance.dtos.wage.request.WagePutRequest;
import com.fuzari.finance.dtos.wage.response.WageGetResponse;
import com.fuzari.finance.mapper.WageMapper;
import com.fuzari.finance.services.WageService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Tag(name = "Wage API", description = "Wage related endpoints")
public class WageController {
  /*
   * TODO
   *
   * Change Wage endpoints with '/{id}/', in order to get the user's id from logged information
   * by implementing UserDetails Interface and Spring Security Login
   * Making the Wage's endpoints more consistent and secure.
   *
   * */


  private final WageService service;
  private final WageMapper MAPPER;

  @GetMapping("/{id}/wage")
  public ResponseEntity<WageGetResponse> getUserWage(@PathVariable(name = "id") UUID user_id) {
    var response = MAPPER.wageToWageGetResponse(service.getUserWage(user_id));

    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @PostMapping("/{id}/wage")
  public ResponseEntity<WageGetResponse> addUserWage(@PathVariable(name = "id") UUID user_id, @RequestBody WagePostRequest wagePostRequest) {
    var wage_to_save = MAPPER.wagePostResponseToWage(wagePostRequest, user_id);
    var response = MAPPER.wageToWageGetResponse(service.createUserWage(wage_to_save));

    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @PutMapping("/{id}/wage")
  public ResponseEntity<Void> updateUserWage(@PathVariable(name = "id") UUID user_id, @RequestBody WagePutRequest wagePutRequest) {
    var wage_to_update = MAPPER.wagePutResponseToWage(wagePutRequest, user_id);
    service.updateUserWage(wage_to_update);

    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
