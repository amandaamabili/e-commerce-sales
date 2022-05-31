package com.santander.sales.service;

import com.santander.sales.dto.ProductDTO;
import com.santander.sales.exception.UserCartNotFoundException;
import com.santander.sales.model.Cart;
import com.santander.sales.repository.CartRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CartService implements CartServiceInterface {

    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Mono<Cart> create(String userID) {
        return cartRepository.findByUserID(userID)
                .switchIfEmpty(cartRepository.save(new Cart(userID)));
    }

    @Override
    public Mono<Cart> update(String userID, ProductDTO dto) {
        return cartRepository
                .findByUserID(userID)
                .flatMap(cart -> {
                    cart.updateProductList(dto);
                    return cartRepository.save(cart);
                })
                .switchIfEmpty(Mono.error(new UserCartNotFoundException(userID)));
    }
    @Override
    public Mono<Cart> get(String cartID) {
        return cartRepository.findById(cartID);
    }
}
