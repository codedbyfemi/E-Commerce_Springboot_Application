package com.example.e_commerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageDTO {
    private Long id;
    private String fileName;
    private String fileType;
    private  byte[] image;
    private String downloadUrl;
}
