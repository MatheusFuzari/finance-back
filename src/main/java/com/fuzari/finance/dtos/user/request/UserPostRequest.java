package com.fuzari.finance.dtos.user.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.UUID;
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
public class UserPostRequest {
  @NotBlank(message = "The field 'name' can't be null or empty")
  private String name;

  @NotBlank(message = "The field 'email' can't be null or empty")
  @Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}", message = "The field 'email' isn't in e-mail standard")
  private String email;

  private String google_id;

  @NotBlank(message = "The field 'password' can't be null or empty")
  private String password;
}
