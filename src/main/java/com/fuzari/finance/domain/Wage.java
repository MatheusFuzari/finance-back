package com.fuzari.finance.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Entity
@Table(name = "wage")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With
public class Wage {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.REMOVE)
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;

  @Column(nullable = false, scale = 2)
  private BigDecimal wage;

  @Column(nullable = false)
  private String wage_date;
  private String wage_advance_date;

  private BigDecimal meal_voucher;
  private BigDecimal health_benefits;
  private BigDecimal transport_benefits;
  private BigDecimal education_benefits;
}
