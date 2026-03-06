package com.fuzari.finance.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Table(name = "expense")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Expense {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  private String name;

  @Column(precision = 10, scale = 2)
  private BigDecimal value;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "expense_type_id")
  private ExpenseType expense_type;

  @CreationTimestamp
  @Column(updatable = false)
  private LocalDate created_at;

  private LocalDate updated_at;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "topic_id")
  private Topic topic;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "sheet_id")
  private Sheet sheet;

  @Enumerated(EnumType.STRING)
  private ExpenseMethods expense_methods;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "benefit_id")
  private Benefit benefit;
}
