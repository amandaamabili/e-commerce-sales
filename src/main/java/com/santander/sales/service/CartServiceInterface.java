package com.santander.sales.service;

import com.santander.sales.dto.CartDTO;
import com.santander.sales.dto.ProductDTO;

public interface CartServiceInterface {

    CartDTO create(String userID);

    CartDTO update(String userID, ProductDTO dto);

    CartDTO get(String cartID);
}
