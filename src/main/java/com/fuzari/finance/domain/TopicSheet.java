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
import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

@Table(name = "topic_sheet")
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TopicSheet {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "topic_id", updatable = false, nullable = false)
  private Topic topic;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name="sheet_id", updatable = false, nullable = false)
  private Sheet sheet;

  @ColumnDefault("false")
  private Boolean applied_to_all;

  @CreationTimestamp
  @Column(updatable = false)
  private LocalDate created_at;
}
