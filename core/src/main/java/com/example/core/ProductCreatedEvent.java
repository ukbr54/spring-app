package com.example.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor @NoArgsConstructor
@Setter @Getter
public class ProductCreatedEvent {
    private String productId;
    private String title;
    private BigDecimal prices;
    private Integer quantity;
}
