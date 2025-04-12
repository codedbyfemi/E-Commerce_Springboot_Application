package com.example.e_commerce.request;

import com.example.e_commerce.model.Category;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateProductRequest {
    private String name;
    private String description;
    private BigDecimal price;
    private String brand;
    private int inventory;

    private Category category;
}
