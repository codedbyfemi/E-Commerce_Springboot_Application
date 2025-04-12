package com.example.e_commerce.service.category;

import com.example.e_commerce.exception.AlreadtExistsException;
import com.example.e_commerce.model.Category;
import com.example.e_commerce.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.e_commerce.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService{

    final private CategoryRepository categoryRepository;

    @Override
    public Category addCategory(Category category) {
        return Optional.of(category).filter(
                c->!categoryRepository.existsByName(c.getName()))
                .map(categoryRepository::save).orElseThrow(
                        ()->new AlreadtExistsException("Category Already Exists"));
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category not found"));
    }

    @Override
    public Category getCategoryByName(String name) {
        return null;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category updateCategory(Category category, Long id) {
        return Optional.ofNullable(getCategoryById(id))
                .map(oldCategory->{
                    oldCategory.setName(category.getName());
                    return categoryRepository.save(oldCategory);
                }).orElseThrow(()->new ResourceNotFoundException("Category not found"));
    }

    @Override
    public void deleteCategoryById(Long id) {
        categoryRepository.findById(id).ifPresentOrElse(categoryRepository::delete, ()->{
            throw new ResourceNotFoundException("Category not found");
        });

    }
}
