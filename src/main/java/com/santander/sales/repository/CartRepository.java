package com.santander.sales.repository;


import com.santander.sales.model.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartRepository extends MongoRepository<Cart, String> {

    Cart findByUserID(String userID);
}
