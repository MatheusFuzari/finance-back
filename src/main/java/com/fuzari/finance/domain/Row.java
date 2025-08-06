package com.fuzari.finance.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.With;

@Entity
@Table(name = "row")
@Data
@AllArgsConstructor
@Builder
@With
public class Row {

  private long row_id;

  private Sheet sheet;
  private LocalDateTime creation_date;
}
