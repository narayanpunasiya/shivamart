package com.example.shivamart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.example.shivamart.entity.Cart;
import com.example.shivamart.entity.User;

public interface CartRepository extends JpaRepository<Cart, Long> {

  Optional<Cart> findByUser(User user);
  
}
