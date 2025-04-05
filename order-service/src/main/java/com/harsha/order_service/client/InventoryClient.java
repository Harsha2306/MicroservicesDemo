package com.harsha.order_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "INVENTORY-SERVICE")
public interface InventoryClient {
  @GetMapping("api/inventories")
  ResponseEntity<Boolean> isInStock(
      @RequestParam(value = "skuCode") String skuCode,
      @RequestParam(value = "quantity") Integer quantity);
}
