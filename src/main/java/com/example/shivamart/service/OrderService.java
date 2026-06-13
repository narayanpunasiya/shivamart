package com.example.shivamart.service;

import com.example.shivamart.entity.Order;
import com.example.shivamart.entity.OrderStatus;

import java.util.*;

public interface OrderService {

  Order checkout(Long userId);

  List<Order> getUserOrders(Long userId);

  Order updateOrderStatus(
    Long orderId,
    OrderStatus status
  );
  
}
