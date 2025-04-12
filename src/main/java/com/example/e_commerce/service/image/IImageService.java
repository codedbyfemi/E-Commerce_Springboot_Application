package com.example.e_commerce.service.image;

import com.example.e_commerce.dto.ImageDTO;
import com.example.e_commerce.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService {
    List<ImageDTO> saveImages(List<MultipartFile> files, Long productId);
    Image getImageById(Long id);
    void updateImage(MultipartFile file, Long id);
    void deleteImageById(Long imageId);
}
