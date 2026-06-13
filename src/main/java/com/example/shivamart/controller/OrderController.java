package com.example.shivamart.controller;

import com.example.shivamart.dto.UpdateOrderStatusRequest;
import com.example.shivamart.entity.Order;
import com.example.shivamart.service.OrderService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


        @PreAuthorize("hasRole('CUSTOMER')")
        @PostMapping("/checkout/{userId}")
        public ResponseEntity<Order> checkout(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                orderService.checkout(userId)
        );
    }
        @PreAuthorize("hasRole('CUSTOMER')")  
        @GetMapping("/user/{userId}")
        public ResponseEntity<List<Order>> getUserOrders(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                orderService.getUserOrders(userId)
        );
    }
        @PreAuthorize("hasRole('ADMIN')")
        @PutMapping("/status/{orderId}")
        public ResponseEntity<Order> updateStatus(
            @PathVariable Long orderId,
            @RequestBody UpdateOrderStatusRequest request) {

        return ResponseEntity.ok(
                orderService.updateOrderStatus(
                        orderId,
                        request.getStatus()
                )
        );
    }
}