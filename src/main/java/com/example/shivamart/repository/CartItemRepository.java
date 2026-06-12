package com.example.shivamart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.shivamart.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
  
}
