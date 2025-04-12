package com.example.e_commerce.contoller;


import com.example.e_commerce.dto.ImageDTO;
import com.example.e_commerce.model.Image;
import com.example.e_commerce.response.ApiResponse;
import com.example.e_commerce.service.image.IImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.web.servlet.function.RequestPredicates.contentType;

@RestController
@RequestMapping("${api.prefix}/images")
@RequiredArgsConstructor
public class ImageController  {

    private final IImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse> saveImages(@RequestParam List<MultipartFile> files, @RequestParam Long productId){

        try {
            List<ImageDTO> imageDTOs = imageService.saveImages(files, productId);
            return ResponseEntity.ok(new ApiResponse("Upload Successful", imageDTOs));
        } catch (Exception e) {
             return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Upload Failed!!", e.getMessage()));
        }

    }


    @PostMapping("/images/download/{id}")
    public ResponseEntity<Resource> downloadImage(@PathVariable Long id) throws SQLException {
        Image image = imageService.getImageById(id);
        ByteArrayResource resource = new ByteArrayResource(image.getImage().getBytes(1, (int) image.getImage().length()));
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(image.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attatchment: filename-\"" + image.getFileName() + "\"")
                .body(resource);

    }




    public ResponseEntity<ApiResponse> updateImage(@RequestBody MultipartFile file,@PathVariable Long id){
         
    }




    public ResponseEntity<ApiResponse> deleteImageById(Long imageId);
}
