package com.fuzari.finance.dtos.wage.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class WagePostRequest {

  @NotNull(message = "The field 'wage' can't be null")
  @Positive(message = "The field 'wage' can't be less than zero")
  private BigDecimal wage;
  @NotBlank(message = "The field 'wage_date' can't be null or empty")
  private String wage_date;
  private String wage_advance_date;
  @Positive(message = "The field 'meal_voucher' can't be less than zero")
  private BigDecimal meal_voucher;
  @Positive(message = "The field 'health_benefits' can't be less than zero")
  private BigDecimal health_benefits;
  @Positive(message = "The field 'transport_benefits' can't be less than zero")
  private BigDecimal transport_benefits;
  @Positive(message = "The field 'education_benefits' can't be less than zero")
  private BigDecimal education_benefits;
}
