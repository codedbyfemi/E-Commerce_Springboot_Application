package com.example.e_commerce.repository;

import com.example.e_commerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByName(String name);

    List<Product> findByBrand(String brand);

    List<Product> findByCategoryName(String category);

    List<Product> findByBrandAndName(String brand, String name);

    List<Product> findByCategoryNameAndName(String category, String name);

    List<Product> findByBrandAndCategoryName(String brand, String category);

    Long countByBrandAndName(String brand, String name);
}
