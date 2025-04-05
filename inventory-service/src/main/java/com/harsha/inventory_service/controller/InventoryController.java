package com.harsha.inventory_service.controller;

import com.harsha.inventory_service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/inventories")
@RequiredArgsConstructor
public class InventoryController {
  private final InventoryService inventoryService;

  @GetMapping
  public ResponseEntity<Boolean> isInStock(
      @RequestParam String skuCode, @RequestParam Integer quantity) {
    return ResponseEntity.ok(inventoryService.isInStock(skuCode, quantity));
  }
}
