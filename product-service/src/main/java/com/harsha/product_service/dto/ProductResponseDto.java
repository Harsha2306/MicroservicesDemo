package com.harsha.product_service.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductResponseDto(String id, String name, String description, BigDecimal price) {}
