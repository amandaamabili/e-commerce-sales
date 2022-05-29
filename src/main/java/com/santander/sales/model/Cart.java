package com.santander.sales.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document("cart")
public class Cart {

    @Id
    private String _id;
    private String userID;
    private List<Product> productListID;
    private double totalPrice;
}
