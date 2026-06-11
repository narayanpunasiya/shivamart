package com.example.shivamart.service;

import com.example.shivamart.entity.Category;
import java.util.*;

public interface CategoryService {

  Category createCategory (Category category);

  List<Category> getAllCategories();

  Category getCategoryById(Long id);

  Category updateCategory(Long id, Category category);

  void deleteCategory(Long id);


  
}
