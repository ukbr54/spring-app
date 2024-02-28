package com.example.products.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter @Getter @NoArgsConstructor
public class CreateProductRestModel {
    private String title;
    private BigDecimal prices;
    private Integer quantity;
}
