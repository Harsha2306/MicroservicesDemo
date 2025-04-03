package com.harsha.order_service.dto;

import java.math.BigDecimal;

public record OrderRequestDto(
    String orderNumber, String skuCode, BigDecimal price, Integer quantity) {}
