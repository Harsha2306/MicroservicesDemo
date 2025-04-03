package com.harsha.order_service.controller;

import com.harsha.order_service.dto.OrderRequestDto;
import com.harsha.order_service.dto.OrderResponseDto;
import com.harsha.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/orders")
@RequiredArgsConstructor
public class OrderController {
  private final OrderService orderService;

  @PostMapping
  public ResponseEntity<OrderResponseDto> createOrder(
      @RequestBody OrderRequestDto orderRequestDto) {
    OrderResponseDto placedOrder = orderService.placeOrder(orderRequestDto);
    URI uri =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(placedOrder.id())
            .toUri();
    return ResponseEntity.created(uri).body(placedOrder);
  }
}
