package com.example.shivamart.entity;

import jakarta.persistence.* ;
import lombok.*;
import java.util.*;
import lombok.AllArgsConstructor;
import lombok.Data; 
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Data
@Entity
@Table(name = "carts")
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  @JoinColumn(name = "user_id", unique = true)
  private User user;

  @JsonManagedReference
  @OneToMany(
    mappedBy = "cart", 
    cascade = CascadeType.ALL, 
    orphanRemoval = true
  )

  private List<CartItem> items = new ArrayList<>();
  
}
