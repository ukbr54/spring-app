package com.example.estore.productService.rest;

import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    @PostMapping
    public String createProduct(){
        return "HTTP Post Handled";
    }

    @GetMapping
    public String getProduct(){
        return "HTTP GET Handled";
    }

    @PutMapping
    public String updateProduct(){
        return "HTTP PUT Handled";
    }

    @DeleteMapping
    public String deleteProduct(){
        return "HTTP Delete Handled";
    }
}
