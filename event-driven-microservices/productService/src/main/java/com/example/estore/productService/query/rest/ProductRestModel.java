package com.example.estore.productService.query.rest;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ProductRestModel {

    private  String productId;
    private  String title;
    private  BigDecimal price;
    private  Integer quantity;
}
