package com.santander.sales.handler;


import com.santander.sales.service.SaleService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class SaleHandler {

    private final SaleService saleService;

    public SaleHandler(SaleService saleService) {
        this.saleService = saleService;
    }

    public Mono<ServerResponse> create(ServerRequest request) {
        return Mono.just(request.pathVariable("userID"))
                .flatMap(saleService::create)
                .flatMap(sale -> ServerResponse.ok().bodyValue(sale))
                .switchIfEmpty(ServerResponse.unprocessableEntity().bodyValue("Criação de vendas inválida"));
    }
}
