package com.fuzari.finance.dtos.wage.response;

import com.fuzari.finance.domain.User;
import com.fuzari.finance.dtos.user.response.UserGetResponse;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.Data;

@Data
public class WageGetResponse {

  private UUID wage_id;
  private UserGetResponse user;
  private BigDecimal wage;
  private String wage_date;
  private String wage_advance_date;
  private BigDecimal meal_voucher;
  private BigDecimal health_benefits;
  private BigDecimal transport_benefits;
  private BigDecimal education_benefits;
}
