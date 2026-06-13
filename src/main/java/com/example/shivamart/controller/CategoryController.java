package com.example.shivamart.controller;

import com.example.shivamart.entity.Category;
import com.example.shivamart.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import org.springframework.security.access.prepost.PreAuthorize;


@RestController
@RequestMapping("/api/categories")
public class CategoryController {
  private final CategoryService categoryService;

  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping
  public ResponseEntity<Category> createCategory(@RequestBody Category category) {
    Category createdCategory = categoryService.createCategory(category);
    return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
  }

  @PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
  @GetMapping
  public ResponseEntity<List<Category>> getAllCategories() {
    List<Category> categories = categoryService.getAllCategories();
    return new ResponseEntity<>(categories, HttpStatus.OK);
  }

  @PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
  @GetMapping("/{id}")
  public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
    Category category = categoryService.getCategoryById(id);
    return new ResponseEntity<>(category, HttpStatus.OK);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PutMapping("/{id}")
  public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category categoryDetails) {
    Category updatedCategory = categoryService.updateCategory(id, categoryDetails);
    return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
    categoryService.deleteCategory(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }


  
}
