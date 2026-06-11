package com.example.shivamart.service.impl;

import com.example.shivamart.service.CategoryService;
import com.example.shivamart.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;  
import java.util.*;
import com.example.shivamart.repository.CategoryRepository;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;

  @Override
  public Category createCategory(Category category) {
    return categoryRepository.save(category);
  }


  
  @Override
  public List<Category> getAllCategories() {
    return categoryRepository.findAll();
  }

  @Override
  public Category getCategoryById(Long id) {
    return categoryRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
  }

  @Override
  public Category updateCategory(Long id, Category categoryDetails) {
    Category existingCategory = getCategoryById(id);
    existingCategory.setName(categoryDetails.getName());
    existingCategory.setDescription(categoryDetails.getDescription());
    return categoryRepository.save(existingCategory);
  } 
  
  @Override
  public void deleteCategory(Long id) {
    categoryRepository.deleteById(id);
  } 
}
