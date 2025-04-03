package com.harsha.product_service.dto;

import java.math.BigDecimal;

public record ProductRequestDto(String name, String description, BigDecimal price) {}
