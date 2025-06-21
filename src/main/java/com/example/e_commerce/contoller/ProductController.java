package com.example.e_commerce.contoller;

import com.example.e_commerce.iService.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/${api.prefix}/product")
public class ProductController {
    @Autowired
    private IProductService productService;
}
