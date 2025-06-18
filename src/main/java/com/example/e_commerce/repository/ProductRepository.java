package com.example.e_commerce.repository;

import com.example.e_commerce.model.Category;
import com.example.e_commerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByName(String name);

    List<Product> findByBrand(String brand);

    List<Product> findByCategory(Category category);

    Product findByBrandAndName(String brand, String name);

    Product findByCategoryAndName(Category category, String name);

    List<Product> findByBrandAndCategory(String brand, Category category);

}
