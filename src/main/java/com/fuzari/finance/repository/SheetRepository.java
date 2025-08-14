package com.fuzari.finance.repository;

import com.fuzari.finance.domain.Sheet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SheetRepository extends JpaRepository<Sheet, UUID> {

  List<Sheet> findAllByUserId(UUID userId);

  @Query("SELECT s FROM Sheet s RIGHT JOIN User u ON u.id = s.user WHERE s.user.id = ?1")
  Optional<Sheet> findCurrentSheetByUserId(UUID userId);
}
