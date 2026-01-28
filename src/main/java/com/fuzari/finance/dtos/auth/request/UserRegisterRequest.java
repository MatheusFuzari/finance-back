package com.fuzari.finance.dtos.auth.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@Builder
@With
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequest {
  @NotBlank(message = "The field 'name' can't be null or empty")
  @Schema(description = "User's name", example = "George Lucas")
  private String name;

  @NotBlank(message = "The field 'email' can't be null or empty")
  @Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}", message = "The field 'email' isn't in e-mail standard")
  @Schema(description = "User's e-mail", example = "george.lucas@email.com")
  private String email;

  @NotBlank(message = "The field 'password' can't be null or empty")
  @Schema(description = "User's password", example = "strongpassword123")
  private String password;
}
