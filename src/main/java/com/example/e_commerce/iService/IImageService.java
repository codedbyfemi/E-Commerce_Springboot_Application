package com.example.e_commerce.iService;

import com.example.e_commerce.dto.ImageDTO;
import com.example.e_commerce.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService {
    List<ImageDTO> saveImages(List<MultipartFile> files, String product);
    List<ImageDTO> getImagesByProduct(String product);
    void updateImage(MultipartFile file, String product);
    void deleteImageById(Long imageId);  //i don't know what do about this yet
}
