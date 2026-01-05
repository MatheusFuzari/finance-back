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
import lombok.NoArgsConstructor;
import lombok.With;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "sheet_cell")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With
public class Cell {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private int y_loc;

  private int x_loc;

  private String value;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  private Sheet sheet;

  @jakarta.persistence.Column(updatable = false)
  @CreationTimestamp
  private LocalDateTime creation_date;
}
