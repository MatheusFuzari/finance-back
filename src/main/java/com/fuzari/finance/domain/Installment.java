package com.fuzari.finance.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Table(name = "installment")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Installment {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  private Integer num_installment;

  @Column(precision = 10, scale = 2)
  private BigDecimal full_value;

  @Column(precision = 10, scale = 2)
  private BigDecimal installment_value;

  @CreationTimestamp
  @Column(updatable = false)
  private LocalDate started_at;
  private LocalDateTime end_at;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "expense_id")
  private Expense expense;

}
