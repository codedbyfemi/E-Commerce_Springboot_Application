package com.example.e_commerce.repository;

import com.example.e_commerce.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
