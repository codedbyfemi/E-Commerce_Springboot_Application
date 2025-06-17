package com.example.e_commerce.iService;

import com.example.e_commerce.model.Product;
import com.example.e_commerce.dto.ProductDTO;

import java.util.List;

public interface IProductService {

    Product addProduct(ProductDTO product);
    List<Product> getProductByName(String name);
    List<Product> getProductByBrand(String brand);
    List<Product> getProductByCategory(String category);
    List<Product> getProductByCategoryAndName(String category, String name);
    List<Product> getProductByBrandAndName(String brand, String name);
    List<Product> getProductByBrandAndCategory(String brand, String category);
    List<Product> getAllProducts();
    Long countProductsByBraneAndName(String brand, String name);
    Product updateProduct(ProductDTO product, Long id);
    void deleteProduct(String productName);



}
