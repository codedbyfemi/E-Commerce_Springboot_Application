package com.example.e_commerce.repository;

import com.example.e_commerce.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String categoryName);
}
