package com.example.shivamart.entity;

import jakarta.persistence.* ;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity 
@Data
@NoArgsConstructor  
@AllArgsConstructor
@Table(name = "categories")
public class Category {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@Column(nullable = false, unique = true)
private String name;

@JsonManagedReference
@OneToMany(mappedBy = "category")
private List<Product> products;


private String description;
  
}
