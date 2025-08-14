package com.fuzari.finance.repository;

import com.fuzari.finance.domain.Wage;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WageRepository extends JpaRepository<Wage, UUID> {

  @Query("SELECT w FROM Wage w RIGHT JOIN User u ON w.user = u.id WHERE u.id = ?1")
  Optional<Wage> findWageByUserId(UUID userId);
}
