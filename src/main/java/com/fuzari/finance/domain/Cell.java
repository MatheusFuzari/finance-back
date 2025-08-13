package com.fuzari.finance.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.With;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "cell")
@Data
@AllArgsConstructor
@Builder
@With
public class Cell {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  private Row row;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  private Column column;

  @jakarta.persistence.Column(updatable = false)
  @CreationTimestamp
  private LocalDateTime creation_date;
}
