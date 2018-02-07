package com.fortech.stockservice.repository;

import com.fortech.stockservice.model.Stock;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends MongoRepository<Stock, String>{
}
