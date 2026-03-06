package com.fuzari.finance.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

@Table(name = "topic")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Topic {

  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  private String topic;

  @ColumnDefault("false")
  private Boolean custom;

  @CreationTimestamp
  @Column(updatable = false)
  private LocalDateTime created_at;

  @ManyToOne(fetch = FetchType.LAZY, optional = true)
  @JoinColumn(name = "user_id", nullable = true, updatable = false)
  private User created_by;
}
