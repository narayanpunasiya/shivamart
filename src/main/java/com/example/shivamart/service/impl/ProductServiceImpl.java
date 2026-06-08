package com.example.shivamart.service.impl;

import com.example.shivamart.repository.ProductRepository;

import com.example.shivamart.entity.Product;
import com.example.shivamart.service.ProductService;
import java.util.List;
import org.springframework.stereotype.Service;



@Service
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  ProductServiceImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }


  @Override
  public Product createProduct(Product product) {
    return productRepository.save(product);
  }

  @Override
  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }

  @Override
  public Product getProductById(Long id) {
    return productRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("Product not found with id: " + id) );
  }

  @Override
  public Product updateProduct(Long id, Product product) {
    Product existingProduct = getProductById(id);

    existingProduct.setName(product.getName());
    existingProduct.setDescription(product.getDescription());
    existingProduct.setType(product.getType());
    existingProduct.setPrice(product.getPrice());
    existingProduct.setQuantity(product.getQuantity());
    existingProduct.setImageUrl(product.getImageUrl());
    return productRepository.save(existingProduct);
  }

  @Override
  public void deleteProduct(Long id) {
    productRepository.deleteById(id);
  }

  
}
