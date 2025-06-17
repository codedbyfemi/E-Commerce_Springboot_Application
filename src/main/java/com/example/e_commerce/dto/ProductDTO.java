package com.example.e_commerce.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String brand;
    private int inventory;
    private String category;
}
