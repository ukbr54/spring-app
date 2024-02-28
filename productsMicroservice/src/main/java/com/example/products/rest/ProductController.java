package com.example.products.rest;

import com.example.products.model.CreateProductRestModel;
import com.example.products.model.ErrorMessage;
import com.example.products.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/products")
public class ProductController {
    private Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    private ProductService productService;
    public ProductController(ProductService productService){
        this.productService = productService;
    }
    @PostMapping
    public ResponseEntity<Object> createProduct(@RequestBody CreateProductRestModel product){
        String productId;
        try {
            productId = productService.createProduct(product);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorMessage(new Date(),e.getMessage(),"/products"));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(productId);
    }
}
