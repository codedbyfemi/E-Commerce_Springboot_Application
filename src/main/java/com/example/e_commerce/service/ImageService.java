package com.example.e_commerce.service;

import com.example.e_commerce.dto.ImageDTO;
import com.example.e_commerce.iService.IImageService;

import com.example.e_commerce.model.Image;
import com.example.e_commerce.model.Product;
import com.example.e_commerce.repository.ImageRepository;
import com.example.e_commerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ImageService implements IImageService {
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<ImageDTO> saveImages(List<MultipartFile> files, String product) {
        return List.of();
    }

    @Override
    public List<ImageDTO> getImagesByProduct(String product) {
        Product product1 = productRepository
                .findByName(product);
        List<Image> images = imageRepository
                .findByProduct(product1);
        return toDTOList(images);
    }


    @Override
    public void updateImage(MultipartFile file, String product) {

    }


    @Override
    public void deleteImageById(Long imageId) {
        if (!imageRepository.existsById(imageId)) {
            throw new RuntimeException("Image not found");
        }
        imageRepository.deleteById(imageId);
    }

    public ImageDTO toDTO(Image image){
        return new ImageDTO(
                image.getId(),
                image.getFileName(),
                image.getFileType(),
                image.getImage(),
                image.getDownloadUrl()
        );
    }

    public List<ImageDTO> toDTOList(List<Image> images){
        return images.stream()
                .map(image -> toDTO(image))
                .collect(Collectors.toList());
    }
}
