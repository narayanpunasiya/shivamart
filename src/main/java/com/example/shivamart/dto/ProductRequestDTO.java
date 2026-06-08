package com.example.shivamart.dto;

import lombok.Data;

@Data
public class ProductRequestDTO {
  private String name;
  private String description;
  private double price;
  private int quantity;
  
}
