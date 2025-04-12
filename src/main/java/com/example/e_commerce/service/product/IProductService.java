package com.example.e_commerce.service.product;

import com.example.e_commerce.model.Product;
import com.example.e_commerce.request.AddProductRequest;
import com.example.e_commerce.request.UpdateProductRequest;

import java.util.List;

public interface IProductService {

    Product addProduct(AddProductRequest product);
    Product getProductById(Long id);
    List<Product> getProductByName(String name);
    List<Product> getProductByBrand(String brand);
    List<Product> getProductByCategory(String category);
    List<Product> getProductByBrandAndName(String brand, String name);
    List<Product> getProductByCategoryAndName(String category, String name);
    List<Product> getProductByBrandAndCategory(String brand, String category);
    List<Product> getAllProducts();
    Long countProductsByBraneAndName(String brand, String name);
    Product updateProduct(UpdateProductRequest product, Long id);
    void deleteProduct(Long id);



}
