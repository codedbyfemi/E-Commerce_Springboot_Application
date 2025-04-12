package com.example.e_commerce.service.category;

import com.example.e_commerce.model.Category;

import java.util.List;

public interface ICategoryService {
    Category addCategory(Category category);
    Category getCategoryById(Long id);
    Category getCategoryByName(String name);
    List<Category> getAllCategories();
    Category updateCategory(Category category, Long id);
    void deleteCategoryById(Long id);

}
