package com.example.e_commerce.exception;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(String productNotFound) {
        super(productNotFound);
    }
}
