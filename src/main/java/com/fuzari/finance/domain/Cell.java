package com.fuzari.finance.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.With;

@Entity
@Table(name = "cell")
@Data
@AllArgsConstructor
@Builder
@With
public class Cell {
  private long id;

  private Row row;
  private Column column;

  private LocalDateTime creation_date;
}
