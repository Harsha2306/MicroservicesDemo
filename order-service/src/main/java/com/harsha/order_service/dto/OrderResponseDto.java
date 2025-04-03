package com.harsha.order_service.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record OrderResponseDto(
    Long id, String orderNumber, String skuCode, BigDecimal price, Integer quantity) {}
