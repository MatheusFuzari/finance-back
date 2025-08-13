package com.fuzari.finance.dtos.user.response;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
  private UUID user_id;
  private String name;
  private String email;
  private String google_id;
}
