package com.example.e_commerce.service;

import com.example.e_commerce.dto.CategoryDTO;
import com.example.e_commerce.iService.ICategoryService;
import com.example.e_commerce.model.Category;
import com.example.e_commerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public CategoryDTO addCategory(CategoryDTO category) {
        Category categoryEntity = new Category();
        categoryEntity.setName(category.getName().toLowerCase());
        categoryRepository.save(categoryEntity);
        return category;
    }

    @Override
    public CategoryDTO getCategoryByName(String name) {
        Category categoryEntity = categoryRepository.findByName(name.toLowerCase());
        return toDTO(categoryEntity);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> categoryEntities = categoryRepository.findAll();
        return toDTOList(categoryEntities);
    }

    @Override
    public void deleteCategoryById(String category) {
        categoryRepository.deleteById(categoryRepository.findByName(category.toLowerCase()).getId());

    }

    public CategoryDTO toDTO(Category category) {
        return new  CategoryDTO(
                category.getId(),
                category.getName()
        );
    }

    public List<CategoryDTO> toDTOList(List<Category> categories) {
        return categories.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
