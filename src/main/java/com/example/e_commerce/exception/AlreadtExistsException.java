package com.example.e_commerce.exception;

public class AlreadtExistsException extends RuntimeException {
    public AlreadtExistsException(String categoryAlreadyExists) {
        super(categoryAlreadyExists);
    }
}
