package com.fuzari.finance.services;

import com.fuzari.exceptions.NotFoundException;
import com.fuzari.finance.domain.Sheet;
import com.fuzari.finance.domain.User;
import com.fuzari.finance.repository.SheetRepository;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SheetService {

  private final SheetRepository repository;

  public List<Sheet> getMySheets(UUID user_id) {
    return repository.findAllByUserId(user_id);
  }

  public Sheet getMyCurrentSheet(UUID user_id) {
    /*
     * TODO
     * If spreadsheet don't exist, generate a new one
     * Following the boilerplate of the previous, if its the first sheet use the standard columns (described in Whimsical)
     * */

    return repository.findCurrentSheetByUserId(user_id).orElse(createNewSheet(user_id));
  }

  public Sheet createNewSheet(UUID user_id) {
    var user = User.builder().id(user_id).build();

    var new_sheet = Sheet.builder().user(user).build();
    return repository.save(new_sheet);
  }
}
