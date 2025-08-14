package com.fuzari.finance.dtos.wage.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.Data;

@Data
public class WagePutRequest {
  private UUID wage_id;
  private BigDecimal wage;
  private String wage_date;
  private String wage_advance_date;
  private BigDecimal meal_voucher;
  private BigDecimal health_benefits;
  private BigDecimal transport_benefits;
  private BigDecimal education_benefits;
}
