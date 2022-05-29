package com.santander.sales.service;

import com.santander.sales.model.CartDTO;

public interface CartService {

    CartDTO create(String userID);

    CartDTO update(String userID, CartDTO dto);

    CartDTO get(String cartID);
}
