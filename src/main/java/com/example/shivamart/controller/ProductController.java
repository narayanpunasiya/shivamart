package com.example.shivamart.controller;

import org.springframework.web.bind.annotation.*; 
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.List;
import com.example.shivamart.entity.Product;
import com.example.shivamart.service.ProductService;
import lombok.*;
import org.springframework.security.access.prepost.PreAuthorize;


@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

@PreAuthorize("hasRole('ADMIN')")
@PostMapping
  public ResponseEntity<Product> createProduct(@RequestBody Product product) {
    // System.out.println("CREATE PRODUCT API HIT");
    Product createdProduct = productService.createProduct(product);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
  }

  @GetMapping
  @PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
  public ResponseEntity<List<Product>> getAllProducts() {
    List<Product> products = productService.getAllProducts();
    return ResponseEntity.ok(products);
  }

  @GetMapping("/{id}")
  @PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
  public ResponseEntity<Product> getProductById(@PathVariable Long id) {
    Product product = productService.getProductById(id);
    if (product != null) {
      return ResponseEntity.ok(product);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PutMapping("/{id}")
  public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
    Product updatedProduct = productService.updateProduct(id, product);
    if (updatedProduct != null) {
      return ResponseEntity.ok(updatedProduct);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PreAuthorize("hasRole('ADMIN')")
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
    productService.deleteProduct(id);
    return ResponseEntity.noContent().build();
    
  }
  
}
