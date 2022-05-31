package com.santander.sales.service;

import com.santander.sales.dto.ProductDTO;
import com.santander.sales.model.Cart;
import reactor.core.publisher.Mono;

public interface CartServiceInterface {

    Mono<Cart> create(String userID);
    Mono<Cart> get(String userID);
    Mono<Cart> update(String userID, ProductDTO dto);
}
