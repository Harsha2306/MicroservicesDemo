package com.harsha.inventory_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "t_inventories")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Inventory {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String skuCode;
  private Integer quantity;
}
