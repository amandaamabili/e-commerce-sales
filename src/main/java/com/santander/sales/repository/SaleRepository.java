package com.santander.sales.repository;

import com.santander.sales.model.Sale;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface SaleRepository extends ReactiveMongoRepository<Sale, String> {

}
