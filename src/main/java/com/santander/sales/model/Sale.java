package com.santander.sales.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.*;

@Document("sales")
public class Sale {

    @Id
    private String _id;
    @Field
    private final String userID;
    @Field
    private final List<Product> productList;
    @Field
    private final double totalPrice;
    @Field
    private final Date purchaseDate;

    public Sale(String userID) {
        this.userID = userID;
        this.productList = new ArrayList<>();
        this.totalPrice = 0;
        this.purchaseDate = new Date();
    }

    public Sale(Cart cart) {
        this.userID = cart.getUserID();
        this.productList = cart.mapToProductList();
        this.totalPrice = cart.getTotalPrice();
        this.purchaseDate = new Date();
    }

}
