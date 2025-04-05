package com.harsha.order_service.service;

import com.harsha.order_service.client.InventoryClient;
import com.harsha.order_service.dto.OrderRequestDto;
import com.harsha.order_service.dto.OrderResponseDto;
import com.harsha.order_service.exception.OutOfStockException;
import com.harsha.order_service.model.Order;
import com.harsha.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderService {
  private final OrderRepository orderRepository;
  private final InventoryClient inventoryClient;

  public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) {
    if (!Optional.ofNullable(
            inventoryClient
                .isInStock(orderRequestDto.skuCode(), orderRequestDto.quantity())
                .getBody())
        .orElse(false))
      throw new OutOfStockException(
          "Product with skuCode " + orderRequestDto.skuCode() + " is not in stock");
    Order savedOrder = orderRepository.save(mapToOrder(orderRequestDto));
    log.info("order with id {} saved", savedOrder.getId());
    return mapToOrderResponseDto(savedOrder);
  }

  private Order mapToOrder(OrderRequestDto orderRequestDto) {
    return Order.builder()
        .orderNumber(orderRequestDto.orderNumber())
        .skuCode(orderRequestDto.skuCode())
        .price(orderRequestDto.price())
        .quantity(orderRequestDto.quantity())
        .build();
  }

  private OrderResponseDto mapToOrderResponseDto(Order order) {
    return OrderResponseDto.builder()
        .id(order.getId())
        .orderNumber(order.getOrderNumber())
        .skuCode(order.getSkuCode())
        .price(order.getPrice())
        .quantity(order.getQuantity())
        .build();
  }
}
