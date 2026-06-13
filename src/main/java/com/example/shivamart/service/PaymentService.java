package com.example.shivamart.service;

import com.example.shivamart.entity.Payment;

public interface PaymentService {

  Payment createPayment(Long orderId);

  Payment markPaymentSuccess(Long paymentId);

  Payment markPaymentFailed(Long paymentId);
  
}
