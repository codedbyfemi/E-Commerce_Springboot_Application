package com.example.e_commerce.iService;

import com.example.e_commerce.model.Product;
import com.example.e_commerce.dto.ProductDTO;

import java.util.List;

public interface IProductService {

    ProductDTO addProduct(ProductDTO product);
    ProductDTO getProductByName(String name);
    List<ProductDTO> getProductByBrand(String brand);
    List<ProductDTO> getProductByCategory(String category);
    ProductDTO getProductByCategoryAndName(String category, String name);
    ProductDTO getProductByBrandAndName(String brand, String name);
    List<ProductDTO> getProductByBrandAndCategory(String brand, String category);
    List<ProductDTO> getAllProducts();
    Long countProductsByBrand(String brand);
    Long countProductsByCategory(String category);
    ProductDTO updateProduct(ProductDTO product, Long id);
    void deleteProduct(String productName);



}
