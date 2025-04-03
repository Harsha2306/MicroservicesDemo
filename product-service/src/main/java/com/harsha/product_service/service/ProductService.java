package com.harsha.product_service.service;

import com.harsha.product_service.dto.ProductRequestDto;
import com.harsha.product_service.dto.ProductResponseDto;
import com.harsha.product_service.model.Product;
import com.harsha.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ProductService {
  private final ProductRepository productRepository;

  public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
    Product savedProduct = productRepository.save(mapToProduct(productRequestDto));
    log.info("product saved with id {}", savedProduct.getId());
    return mapToProductResponseDto(savedProduct);
  }

  private Product mapToProduct(ProductRequestDto productRequestDto) {
    return Product.builder()
        .name(productRequestDto.name())
        .description(productRequestDto.description())
        .price(productRequestDto.price())
        .build();
  }

  private ProductResponseDto mapToProductResponseDto(Product product) {
    return ProductResponseDto.builder()
        .id(product.getId())
        .name(product.getName())
        .description(product.getDescription())
        .price(product.getPrice())
        .build();
  }

  public List<ProductResponseDto> findAll() {
    return productRepository.findAll().stream().map(this::mapToProductResponseDto).toList();
  }
}
