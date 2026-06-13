package com.example.shivamart.repository;

import com.example.shivamart.entity.Order;
import com.example.shivamart.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository
        extends JpaRepository<Order, Long> {

    List<Order> findByUser(User user);
}