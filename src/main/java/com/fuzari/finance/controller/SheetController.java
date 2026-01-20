package com.fuzari.finance.controller;

import com.fuzari.finance.services.SheetService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sheet")
@Tag(name = "Sheet API", description = "Sheet related endpoints")
public class SheetController {

  private final SheetService service;

  @GetMapping()
  public ResponseEntity<?> getUserCurrentSheet() {
    var sheet = service.getMySheets();
  }

  @GetMapping()
  public ResponseEntity<?> getSheetDetails() {

  }

  @GetMapping()
  public ResponseEntity<List<?>> getUsersSheetHistory() {

  }

  @PostMapping()
  public ResponseEntity<?> createNewSheet() {

  }

  @PutMapping()
  public ResponseEntity<?> addRow() {

  }

  @PutMapping()
  public ResponseEntity<?> addCol() {

  }

  @PutMapping()
  public ResponseEntity<?> removeRow() {

  }

  @PutMapping()
  public ResponseEntity<?> removeCol() {

  }
}
