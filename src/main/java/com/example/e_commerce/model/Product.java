package com.example.e_commerce.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String brand;
    private int inventory;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Category_Id")
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images;


    public Product(String name, String description, BigDecimal price, String brand, int inventory, Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.brand = brand;
        this.inventory = inventory;
        this.category = category;
    }
}
