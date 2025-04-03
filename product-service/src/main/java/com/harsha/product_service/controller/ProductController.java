package com.harsha.product_service.controller;

import com.harsha.product_service.dto.ProductRequestDto;
import com.harsha.product_service.dto.ProductResponseDto;
import com.harsha.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/products")
public class ProductController {

  private final ProductService productService;

  @PostMapping
  public ResponseEntity<ProductResponseDto> createProduct(
      @RequestBody ProductRequestDto productRequestDto) {
    ProductResponseDto savedProduct = productService.createProduct(productRequestDto);
    URI uri =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedProduct.id())
            .toUri();
    return ResponseEntity.created(uri).body(savedProduct);
  }

  @GetMapping
  public ResponseEntity<List<ProductResponseDto>> getAll() {
    return ResponseEntity.ok(productService.findAll());
  }
}
