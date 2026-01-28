package com.fuzari.finance.dtos.user.response;

import io.swagger.v3.oas.annotations.media.Schema;
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
public class UserGetResponse {
  @Schema(description = "User's id", example = "9bb5000a-4302-40a9-bd5e-d49a4852ce1f")
  private UUID id;
  @Schema(description = "User's name", example = "George Lucas")
  private String name;
  @Schema(description = "User's e-mail", example = "george.lucas@email.com")
  private String email;
//  @Schema(description = "User's Google SSO ID", example = "xxx-yyy-zzz")
//  private String google_id;
}
