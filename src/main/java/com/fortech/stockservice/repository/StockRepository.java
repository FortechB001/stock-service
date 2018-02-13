package com.fortech.stockservice.repository;

import com.fortech.stockservice.model.Stock;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for the Stock entity.
 */
@Repository
public interface StockRepository extends MongoRepository<Stock, String> {

    /**
     * Gets all the available stock locations and quantities.
     *
     * @param productId - the productId
     * @param quantity  - the quantity
     * @return - list of Stocks
     */
    List<Stock> findAllByProductIdAndQuantityIsGreaterThan(String productId, Integer quantity);

    /**
     * Get all Stock by Product Id
     *
     * @param productId - the productId.
     * @return
     */
    List<Stock> findAllByProductId(String productId);

    /**
     * Gets all the stock info by productIdAndLocation.
     *
     * @param productId - the productId.
     * @param location  - the location.
     * @return - list of stock entities.
     */
    List<Stock> findAllByProductIdAndLocation(String productId, String location);

    /**
     * Delete al stock for a product
     *
     * @param productId - product Id
     * @return true / false
     */
    Long deleteAllByProductId(String productId);
}
