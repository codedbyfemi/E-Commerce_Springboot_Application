package com.example.e_commerce.service.product;

import com.example.e_commerce.exception.ProductNotFoundException;
import com.example.e_commerce.model.Category;
import com.example.e_commerce.model.Product;
import com.example.e_commerce.repository.CategoryRepository;
import com.example.e_commerce.repository.ProductRepository;
import com.example.e_commerce.request.AddProductRequest;
import com.example.e_commerce.request.UpdateProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.ProviderNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService{
    final private ProductRepository  productRepository;
    final private CategoryRepository categoryRepository;

    @Override
    public Product addProduct(AddProductRequest request) {
        //check if category exists
        Category existingCategory = Optional.ofNullable(categoryRepository.findByName(request.getCategory().getName()))
                .orElseGet(()->{
                    Category newCategory = new Category(request.getCategory().getName());
                    return categoryRepository.save(newCategory);
                });
        request.setCategory(existingCategory);
        return productRepository.save(createProduct(request, existingCategory));
    }
    private Product createProduct(AddProductRequest request, Category category){
        return new Product(request.getName(),  request.getDescription(), request.getPrice(), request.getBrand(), request.getInventory(), category);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProviderNotFoundException("Product not found"));
    }

    @Override
    public List<Product> getProductByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> getProductByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    @Override
    public List<Product> getProductByCategory(String category) {
        return productRepository.findByCategoryName(category);
    }

    @Override
    public List<Product> getProductByBrandAndName(String brand, String name) {
        return productRepository.findByBrandAndName(brand, name);
    }

    @Override
    public List<Product> getProductByCategoryAndName(String category, String name) {
        return productRepository.findByCategoryNameAndName(category, name);
    }

    @Override
    public List<Product> getProductByBrandAndCategory(String brand, String category) {
        return productRepository.findByBrandAndCategoryName(brand, category);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Long countProductsByBraneAndName(String brand, String name) {
        return productRepository.countByBrandAndName(brand, name);
    }

    @Override
    public Product updateProduct(UpdateProductRequest product, Long id) {
        return productRepository.findById(id)
                .map(existingProduct -> updateExsistingProduct(existingProduct, product))
                .map(productRepository::save)
                .orElseThrow(()->new ProductNotFoundException("Product not found"));
    }

    private Product updateExsistingProduct(Product existingProduct, UpdateProductRequest request){
        existingProduct.setName(request.getName());
        existingProduct.setDescription(request.getDescription());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setBrand(request.getBrand());
        existingProduct.setInventory(request.getInventory());
        Category category = categoryRepository.findByName(request.getCategory().getName());
        existingProduct.setCategory(category);
        return existingProduct;
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.findById(id).ifPresentOrElse(productRepository::delete, ()->{
            throw new ProductNotFoundException("Product not found");
        });
    }
}
