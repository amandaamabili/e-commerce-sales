package com.santander.sales.dto;

import com.santander.sales.model.Cart;
import com.santander.sales.model.Product;
import lombok.Data;
import org.springframework.lang.Nullable;

import java.util.Map;
import java.util.Optional;

@Data
public class CartDTO {

    private String id;
    private Map<Long,Product> productMap;
    @Nullable
    private double totalPrice;

    public CartDTO(Cart cart){
        this.id = cart.get_id();
        this.productMap = cart.getProductMap();
        this.totalPrice = Optional.ofNullable(this.getTotalPrice()).orElse(0.0);
    }
}