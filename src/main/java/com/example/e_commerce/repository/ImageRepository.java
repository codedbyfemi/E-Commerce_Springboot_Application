package com.example.e_commerce.repository;

import com.example.e_commerce.model.Image;
import com.example.e_commerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByProduct(Product product);
}
