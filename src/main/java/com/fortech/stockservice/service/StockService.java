package com.fortech.stockservice.service;

import com.fortech.stockservice.model.Stock;
import com.fortech.stockservice.model.dto.StockInfoDto;
import com.fortech.stockservice.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Stock service.
 */
@Service
public class StockService {

    /**
     * The stock repository.
     */
    @Autowired
    private StockRepository stockRepository;

    /**
     * Adds new Stock information.
     *
     * @param productId - the product id.
     * @param location  - the location.
     * @param quantity  - the quantity.
     */
    public void addStock(String productId, String location, Integer quantity) {

        List<Stock> stocks = stockRepository.findAllByProductIdAndLocation(productId, location);

        if (stocks == null || stocks.isEmpty()) {
            Stock.Location loc = Stock.Location.valueOf(location);
            stockRepository.save(new Stock(productId, loc, quantity));
        } else {
            stocks.get(0).setQuantity(stocks.get(0).getQuantity() + quantity);
            stockRepository.save(stocks.get(0));
        }
    }

    public boolean removeFromStock(String productId, Integer requestQuantity) {

        List<Stock> stocks = stockRepository.findAllByProductIdAndQuantityIsGreaterThan(productId, requestQuantity);

        stocks.sort((o1, o2) -> {
            int o1Distance = o1.getLocation().getDistance();
            int o2Distance = o2.getLocation().getDistance();
            return Integer.compare(o1Distance, o2Distance);
        });
        for (Stock stock : stocks) {
            if (requestQuantity > 0) {
                int stockQuantity = stock.getQuantity();
                if (stockQuantity >= requestQuantity) {
                    stock.setQuantity(stockQuantity - requestQuantity);
                    requestQuantity = 0;
                } else {
                    stock.setQuantity(requestQuantity - stockQuantity);
                    requestQuantity -= stockQuantity;
                }
            }
        }
        return requestQuantity == 0 && !stockRepository.save(stocks).isEmpty();
    }

    /**
     * Gets Stock info for a given product id.
     *
     * @param productId - the product id.
     * @return - StockInfo dto.
     */
    public StockInfoDto getStockInfoByProductId(String productId) {
        StockInfoDto stockDto = new StockInfoDto();
        stockDto.setProductId(productId);

        List<Stock> stocks = stockRepository.findAllByProductIdAndQuantityIsGreaterThan(productId, 0);
        int count = 0;
        for (Stock stock : stocks) {
            count += stock.getQuantity();
        }
        stockDto.setStockTotal(count);
        return stockDto;

    }

    public int getDaysToWarehouse(String productId, int howMany) {
        int result = 0;

        List<Stock> stocks = stockRepository.findAllByProductIdAndQuantityIsGreaterThan(productId, 0);
        int totalStock = 0;
        for (Stock stock : stocks) {
            totalStock += stock.getQuantity();
        }

        if (howMany <= totalStock) {
            Collections.sort(stocks, (o1, o2) -> {
                int o1Distance = o1.getLocation().getDistance();
                int o2Distance = o2.getLocation().getDistance();
                return Integer.compare(o1Distance, o2Distance);
            });

            int stockIteratorCount = 0;
            int requiredStock = howMany;
            int remainingStock = totalStock;
            while (remainingStock > 0 && requiredStock > 0 && stockIteratorCount < stocks.size()) {
                Stock currentStock = stocks.get(stockIteratorCount);
                int currentStockQuantity = currentStock.getQuantity();
                int quantityTakenFromCurrentStock = currentStockQuantity > requiredStock ? requiredStock : currentStockQuantity;
                requiredStock -= quantityTakenFromCurrentStock;
                remainingStock -= quantityTakenFromCurrentStock;

                if (result < currentStock.getLocation().getDistance()) {
                    result = currentStock.getLocation().getDistance();
                }
                stockIteratorCount++;
            }
        } else {
            return -1;
        }

        return result;
    }
}
