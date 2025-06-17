package com.example.e_commerce.iService;

import com.example.e_commerce.dto.CategoryDTO;
import com.example.e_commerce.model.Category;

import java.util.List;

public interface ICategoryService {
    CategoryDTO addCategory(CategoryDTO category);
    CategoryDTO getCategoryByName(String name);
    List<CategoryDTO> getAllCategories();
    void deleteCategoryById(String catrgory);

}
