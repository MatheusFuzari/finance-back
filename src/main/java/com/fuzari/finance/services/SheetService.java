package com.fuzari.finance.services;

import com.fuzari.finance.domain.Sheet;
import com.fuzari.finance.domain.User;
import com.fuzari.finance.exceptions.NotFoundException;
import com.fuzari.finance.repository.SheetRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SheetService {

  private final SheetRepository repository;

  public List<Sheet> getMySheets(UUID user_id) {
    return repository.findAllByUserId(user_id);
  }

  public Sheet getMyCurrentSheet(UUID user_id) {
    return repository.findCurrentSheetByUserId(user_id).orElse(createNewSheet(user_id));
  }

  public Sheet createNewSheet(UUID user_id) {
    var user = User.builder().id(user_id).build();

    var new_sheet = Sheet.builder().user(user).build();
    return repository.save(new_sheet);
  }

  public void disableSheetById(UUID sheet_id){
    var sheet_to_update = repository.findById(sheet_id).orElseThrow(() -> new NotFoundException(
        "Sheet not found with id %s".formatted(sheet_id.toString())));

    sheet_to_update.setFinished_at(LocalDate.now());
    repository.save(sheet_to_update);
  }

  public boolean checkSheetIsExpired() {
    var day_number = LocalDate.now().getDayOfWeek().getValue();

    var expiredSheet = repository.checkIfSheetIsExpired(day_number);

    return expiredSheet.isPresent();
  }
}
