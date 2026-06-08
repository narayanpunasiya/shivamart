package com.example.shivamart.repository;

import com.example.shivamart.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
 

public interface ProductRepository extends JpaRepository<Product, Long> {
  
}