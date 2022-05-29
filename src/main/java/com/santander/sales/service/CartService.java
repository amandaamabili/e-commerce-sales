package com.santander.sales.service;

import com.santander.sales.dto.CartDTO;
import com.santander.sales.dto.ProductDTO;
import com.santander.sales.exception.UserCartNotFoundException;
import com.santander.sales.exception.UserHasAlreadyCartException;
import com.santander.sales.model.Cart;
import com.santander.sales.repository.CartRepository;
import org.springframework.stereotype.Service;

@Service
public class CartService implements CartServiceInterface {

    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public CartDTO create(String userID) {
        if (cartRepository.findByUserID(userID).isPresent()) {
            throw new UserHasAlreadyCartException();
        }
        var cartSaved = cartRepository.save(new Cart(userID));
        return new CartDTO(cartSaved);
    }

    @Override
    public CartDTO update(String userID, ProductDTO dto) {
        var cart = cartRepository.findByUserID(userID).orElseThrow(() -> new UserCartNotFoundException(userID));

        cart.updateProductList(dto);
        return new CartDTO(cartRepository.save(cart));
    }

    @Override
    public CartDTO get(String cartID) {
        var cart = cartRepository.findById(cartID).orElseThrow(() -> new UserCartNotFoundException(cartID));
        return new CartDTO(cart);
    }

    @Override
    public CartDTO delete(String userID) {
        var cart = cartRepository.findByUserID(userID).orElseThrow(() -> new UserCartNotFoundException(userID));
        cartRepository.delete(cart);
        return new CartDTO(cart);
    }
}
