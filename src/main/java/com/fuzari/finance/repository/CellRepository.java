package com.fuzari.finance.repository;

import com.fuzari.finance.domain.Cell;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CellRepository extends JpaRepository<Cell, UUID> {

  @Query("SELECT c FROM Cell c RIGHT JOIN Sheet s ON s.id = c.sheet.id WHERE c.sheet.id = ?1")
  List<Cell> getCellsFromSheet(UUID sheet_id);

  @Query("SELECT c FROM Cell c RIGHT JOIN Sheet s ON s.id = c.sheet.id WHERE (c.x_loc != ?1 AND c.y_loc != ?2) AND c.sheet.id = ?3")
  Optional<Cell> findCellInLocationBySheet(int x_loc, int y_loc, UUID sheet_id);
}
