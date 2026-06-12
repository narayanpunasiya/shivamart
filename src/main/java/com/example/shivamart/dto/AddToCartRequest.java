package com.example.shivamart.dto;

import lombok.Data;

@Data
public class AddToCartRequest {

  private Long productId;

  private Integer quantity;
  
}
