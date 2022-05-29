package com.santander.sales.model;

import com.santander.sales.dto.ProductDTO;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.HashMap;
import java.util.Map;

@Data
@Document("carts")
public class Cart {

    @Id
    private String _id;
    @Field
    private String userID;
    @Field
    private Map<Long, Product> productMap;
    @Field
    private double totalPrice;


    public Cart(String userID) {
        this.userID = userID;
        this.productMap = new HashMap<>();
        this.totalPrice = 0;
    }

    public void updateProductList(ProductDTO dto) {
        Product product = this.productMap.get(dto.getId());

        if (product == null) {
            this.productMap.put(dto.getId(), new Product(dto));
            updateTotalPrice();
            return;
        }
        if (dto.getAmount() == 0) {
            this.productMap.remove(dto.getId());
            updateTotalPrice();
            return;
        }
        this.productMap.replace(dto.getId(), new Product(dto));
        updateTotalPrice();
    }

    private void updateTotalPrice() {
        this.totalPrice = calculateTotalPrice();
    }

    private double calculateTotalPrice() {
        return this.productMap
                .values()
                .stream()
                .map(product -> product.getPrice() * product.getAmount())
                .reduce(0.0, Double::sum);
    }
}
