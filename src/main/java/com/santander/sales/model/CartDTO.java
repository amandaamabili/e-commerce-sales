package com.santander.sales.model;

import lombok.Data;

import java.util.List;

@Data
public class CartDTO {

    private String id;
    private List<Product> products;
    private double totalPrice;
}
