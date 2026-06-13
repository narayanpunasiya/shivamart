package com.example.shivamart.entity;

import lombok.*;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "payments")
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  @JoinColumn(name = "order_id")
  private Order order;

  private BigDecimal amount;

  private LocalDateTime paymentDate;

  @Enumerated(EnumType.STRING)
  private PaymentStatus status;

  private String transactionId;


}
