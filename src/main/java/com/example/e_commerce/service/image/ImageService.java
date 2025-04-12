package com.example.e_commerce.service.image;

import com.example.e_commerce.dto.ImageDTO;
import com.example.e_commerce.exception.ResourceNotFoundException;
import com.example.e_commerce.model.Image;
import com.example.e_commerce.model.Product;
import com.example.e_commerce.repository.ImageRepository;
import com.example.e_commerce.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ImageService implements IImageService{

    private final ImageRepository imageRepository;
    private final ProductService productService;

    @Override
    public List<ImageDTO> saveImages(List<MultipartFile> files, Long productId) {
        Product product = productService.getProductById(productId);
        List<ImageDTO> savedImageDto = new ArrayList<>();
        for(MultipartFile file : files){
            try {
                Image image = new Image();
                image.setFileName(file.getOriginalFilename());
                image.setFileType(file.getContentType());
                image.setImage(new SerialBlob(file.getBytes()));
                image.setProduct(product);

                String buildDownloadUrl = "/api/v1/images/image.download";
                String downloadUrl = buildDownloadUrl + image.getId();
                image.setDownloadUrl(downloadUrl);
                Image savedImage = imageRepository.save(image);
                savedImage.setDownloadUrl(buildDownloadUrl+ savedImage.getId());
                imageRepository.save(savedImage);


                ImageDTO imageDTO = new ImageDTO();
                imageDTO.setImageId(savedImage.getId());
                imageDTO.setImageName(savedImage.getFileName());
                imageDTO.setDownloadURL(savedImage.getDownloadUrl());
                savedImageDto.add(imageDTO);
            }catch (IOException | SQLException e){
                throw new RuntimeException(e.getMessage());
            }
        }
        return savedImageDto;
    }

    @Override
    public Image getImageById(Long id) {
        return imageRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("No image foUnd with this ID" + id));
    }

    @Override
    public void updateImage(MultipartFile file, Long id) {
        Image image = getImageById(id);
        try {
            image.setFileName(file.getOriginalFilename());
            image.setFileType(file.getContentType());
            image.setImage(new SerialBlob(file.getBytes()));
            imageRepository.save(image);
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public void deleteImageById(Long imageId) {
        imageRepository.findById(imageId)
                .ifPresentOrElse(imageRepository::delete, ()-> {
                    throw new ResourceNotFoundException("No image foUnd with this ID" + imageId);
                });
    }
}
