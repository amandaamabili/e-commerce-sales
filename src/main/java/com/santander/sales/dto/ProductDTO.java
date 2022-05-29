package com.santander.sales.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ProductDTO {

    private Long id;
    private String name;
    private Double price;
    private Long amount;
}