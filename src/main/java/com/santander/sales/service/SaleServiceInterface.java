package com.santander.sales.service;

import com.santander.sales.model.Sale;
import reactor.core.publisher.Mono;

interface  SaleServiceInterface {

    Mono<Sale> create(String userID);
}
