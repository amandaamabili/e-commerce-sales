package com.santander.sales.config;

import com.santander.sales.handler.CartHandler;
import com.santander.sales.handler.SaleHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class RouterConfig {

    @Bean
    public RouterFunction<?> route(CartHandler cartHandler, SaleHandler saleHandler) {
        return
                RouterFunctions.route()
                        .POST("/sale/cart/user/{userID}", accept(APPLICATION_JSON), cartHandler::addCart)
                        .POST("/sale/user/{userID}", accept(APPLICATION_JSON), saleHandler::create)
                        .PUT("/sale/cart/user/{userID}", accept(APPLICATION_JSON), cartHandler::updateCart)
                        .GET("/sale/cart/user/{userID}", accept(APPLICATION_JSON), cartHandler::getByUser)
                        .build();
    }
}

