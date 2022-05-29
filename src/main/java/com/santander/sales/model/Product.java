package com.santander.sales.model;


import lombok.Data;

@Data
public class Product {

    private String name;
    private Double price;
    private Long stock_quantity;
}
