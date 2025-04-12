package com.example.e_commerce.dto;

import lombok.Data;
import org.springframework.web.bind.annotation.DeleteMapping;

@Data
public class ImageDTO {
    private Long imageId;
    private String imageName;
     private String downloadURL;
}
