package com.example.e_commerce.service;

import com.example.e_commerce.dto.ProductDTO;
import com.example.e_commerce.iService.IProductService;

import com.example.e_commerce.model.Category;
import com.example.e_commerce.model.Image;
import com.example.e_commerce.model.Product;
import com.example.e_commerce.repository.CategoryRepository;
import com.example.e_commerce.repository.ImageRepository;
import com.example.e_commerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductService implements IProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ImageRepository imageRepository;

    @Override
    public ProductDTO addProduct(ProductDTO product) {
        Category category = categoryRepository.findByName(product.getCategory());
        Product product1 = new Product();
        product1.setName(product.getName());
        product1.setDescription(product.getDescription());
        product1.setPrice(product.getPrice());
        product1.setBrand(product.getBrand());
        product1.setInventory(product.getInventory());
        product1.setCategory(category);
        productRepository.save(product1);
        return toDTO(product1);
    }

    @Override
    public ProductDTO getProductByName(String name) {
       Product product = productRepository.findByName(name);
        return toDTO(product);
    }

    @Override
    public List<ProductDTO> getProductByBrand(String brand) {
        List<Product> products = productRepository.findByBrand(brand);
        return toDTOList(products);
    }

    @Override
    public List<ProductDTO> getProductByCategory(String category) {
        Category category1 = categoryRepository.findByName(category);
        List<Product> products = productRepository.findByCategory(category1);
        return toDTOList(products);
    }

    @Override
    public ProductDTO getProductByCategoryAndName(String category, String name) {
        Category category1 = categoryRepository.findByName(category);
        Product product = productRepository.findByCategoryAndName(category1, name);
        return toDTO(product);
    }

    @Override
    public ProductDTO getProductByBrandAndName(String brand, String name) {
        Product product = productRepository.findByBrandAndName(brand, name);
        return toDTO(product);
    }

    @Override
    public List<ProductDTO> getProductByBrandAndCategory(String brand, String category) {
        Category category1 = categoryRepository.findByName(category);
        List<Product> products = productRepository.findByBrandAndCategory(brand, category1);
        return toDTOList(products);
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return toDTOList(products);
    }

    @Override
    public Long countProductsByBrand(String brand) {
        Long count = 0L;
        List<Product> products = productRepository.findByBrand(brand);
        for(Product product : products){
            count++;
        }
        return count;
    }

    @Override
    public Long countProductsByCategory(String category) {
        Long count = 0L;
        Category category1 = categoryRepository.findByName(category);
        List<Product> products = productRepository.findByCategory(category1);
        for(Product product : products){
            count++;
        }
        return count;
    }


    @Override
    public ProductDTO updateProduct(ProductDTO product, Long id) {
        Product product1 = productRepository.findById(id).orElse(null);
        product1.setName(product.getName());
        product1.setDescription(product.getDescription());
        product1.setPrice(product.getPrice());
        product1.setBrand(product.getBrand());
        product1.setInventory(product.getInventory());
        Category category = categoryRepository.findByName(product.getCategory());
        product1.setCategory(category);
        productRepository.save(product1);
        return toDTO(product1);
    }

    @Override
    public void deleteProduct(String productName) {
        Product product = productRepository.findByName(productName);
        List<Image> images = imageRepository.findByProduct(product);
        productRepository.delete(product);
        imageRepository.deleteAll(images);

    }

    public ProductDTO toDTO(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getBrand(),
                product.getInventory(),
                product.getCategory().getName()
        );
    }

    public List<ProductDTO> toDTOList(List<Product> products) {
        return products.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
