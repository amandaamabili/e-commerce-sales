package com.santander.sales.handler;

import com.santander.sales.dto.ProductDTO;
import com.santander.sales.service.CartService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class CartHandler {

    private final CartService cartService;

    public CartHandler(CartService cartService) {
        this.cartService = cartService;
    }

    public Mono<ServerResponse> getByUser(ServerRequest request) {
        return Mono.just(request.pathVariable("userID"))
                .flatMap(cartService::getByUser)
                .flatMap(cart -> ServerResponse.ok().bodyValue(cart))
                .switchIfEmpty(ServerResponse.unprocessableEntity().bodyValue("Invalid user. Check data imput."));
    }

    public Mono<ServerResponse> addCart(ServerRequest request) {
        return Mono.just(request.pathVariable("userID"))
                .flatMap(cartService::create)
                .flatMap(cart -> ServerResponse.ok().bodyValue(cart))
                .switchIfEmpty(ServerResponse.unprocessableEntity().bodyValue("Invalid user. Check data imput."));
    }

    public Mono<ServerResponse> updateCart(ServerRequest request) {
        var userID = request.pathVariable("userID");

        return request.bodyToMono(ProductDTO.class)
                .flatMap(productDTO -> cartService.update(userID, productDTO))
                .flatMap(cart -> ServerResponse.ok().bodyValue(cart))
                .onErrorResume(e -> ServerResponse.notFound().build());
    }
}

