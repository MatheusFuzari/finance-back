package com.fuzari.finance.services;

import com.fuzari.exceptions.SheetAlreadyHaveCellInLocationException;
import com.fuzari.finance.domain.Cell;
import com.fuzari.finance.repository.CellRepository;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CellService {

  private final CellRepository cellRepository;

  public List<Cell> getAllCellsFromSheet(UUID sheet_id) {
    return cellRepository.getCellsFromSheet(sheet_id);
  }

  public Cell createCell(Cell cell_to_save) {
    int x_loc = cell_to_save.getX_loc();
    int y_loc = cell_to_save.getY_loc();
    var sheet_id = cell_to_save.getSheet().getId();

    assertCellIsNotInLocationBySheet(x_loc, y_loc, sheet_id);

    return cellRepository.save(cell_to_save);
  }

  public void assertCellIsNotInLocationBySheet(int x_loc, int y_loc, UUID sheet_id) {
    if(cellRepository.findCellInLocationBySheet(x_loc, y_loc, sheet_id).isPresent()){
      throw new SheetAlreadyHaveCellInLocationException("A Cell is already registered in the position col: %d, row: %d".formatted(x_loc, y_loc));
    }
  }

}
