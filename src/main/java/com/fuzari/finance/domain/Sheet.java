package com.fuzari.finance.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.cglib.core.Local;

@Table(name = "sheet")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With
public class Sheet {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  private User user;

  @CreationTimestamp
  @Column(updatable = false)
  private LocalDate creation_date;

  private LocalDate finish_date;
}
