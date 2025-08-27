package com.fuzari.finance.domain;

import jakarta.persistence.CascadeType;
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
@Table(name = "sheet_column")
@Data
@AllArgsConstructor
@Builder
@With
public class Column {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
  private Sheet sheet;

  @jakarta.persistence.Column(nullable = false)
  private String topic;

  @jakarta.persistence.Column(updatable = false)
  @CreationTimestamp
  private LocalDateTime creation_date;
}
