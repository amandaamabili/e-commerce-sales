package com.santander.sales.service;

import com.santander.sales.exception.UserCartNotFoundException;
import com.santander.sales.exception.UserNotFoundException;
import com.santander.sales.gateway.UsersGateway;
import com.santander.sales.model.Cart;
import com.santander.sales.model.Sale;
import com.santander.sales.repository.CartRepository;
import com.santander.sales.repository.SaleRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.util.HashMap;

@Service
public class SaleService implements SaleServiceInterface {

    private final SaleRepository saleRepository;
    private final CartRepository cartRepository;
    private final UsersGateway usersGateway;


    public SaleService(SaleRepository saleRepository, CartRepository cartRepository, UsersGateway usersGateway) {
        this.saleRepository = saleRepository;
        this.cartRepository = cartRepository;
        this.usersGateway = usersGateway;
    }

    public Mono<Sale> create(String userID) {
        return Mono.zip(
                        Mono.just(userID)
                                .flatMap(cartRepository::findByUserID)
                                .switchIfEmpty(Mono.error(() -> new UserCartNotFoundException(userID))),
                        Mono.just(userID)
                                .flatMap(usersGateway::getUser)
                                .switchIfEmpty(Mono.error(UserNotFoundException::new))
                ).map(Tuple2::getT1)
                .flatMap(cart -> {
                    var c = emptyCart(cart);
                    return c.flatMap(d -> saleRepository.save(new Sale(cart)));
                });
    }

    public Mono<Cart> emptyCart(Cart c) {
        Cart temp = c.clone();
        temp.setProductMap(new HashMap<>());
        return cartRepository.save(temp);
    }
}
