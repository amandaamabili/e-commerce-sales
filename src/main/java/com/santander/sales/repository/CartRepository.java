package com.santander.sales.repository;


import com.santander.sales.model.Cart;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface CartRepository extends ReactiveMongoRepository<Cart, String> {

    Mono<Cart> findByUserID(String userID);
}
