package com.example.shivamart.dto;

import com.example.shivamart.entity.OrderStatus;

import lombok.Data;

@Data
public class UpdateOrderStatusRequest {

    private OrderStatus status;
}