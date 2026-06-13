package com.example.shivamart.service;

import com.example.shivamart.entity.Cart;

public interface CartService {

  Cart getCart(Long userId);

  Cart addToCart(Long userId, Long productId, Integer quantity);

  void removeFromCart(Long cartItemId);
  
  void clearCart(Long userId);
}

