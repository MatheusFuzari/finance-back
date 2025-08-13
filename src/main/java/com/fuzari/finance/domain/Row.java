package com.fuzari.finance.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
@Table(name = "row")
@Data
@AllArgsConstructor
@Builder
@With
public class Row {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID row_id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  private Sheet sheet;

  @CreationTimestamp
  @Column(updatable = false)
  private LocalDateTime creation_date;
}
