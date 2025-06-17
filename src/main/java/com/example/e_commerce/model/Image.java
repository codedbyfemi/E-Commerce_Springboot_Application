package com.example.e_commerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;
import java.sql.Blob;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;
    private String fileType;
    private  byte[] image;
    private String downloadUrl;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
