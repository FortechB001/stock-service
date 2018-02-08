package com.fortech.stockservice.service;

import com.fortech.stockservice.model.Stock;
import com.fortech.stockservice.model.dto.StockInfoDto;
import com.fortech.stockservice.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * Gets Stock info for a given product id.
     *
     * @param productId - the product id.
     * @return - map with locations and quantities.
     */
    public StockInfoDto getStockInfoByProductId(String productId) {
        StockInfoDto stockDto = new StockInfoDto();
        stockDto.setProductId(productId);

        List<Stock> stocks = stockRepository.findAllByProductIdAndQuantityIsGreaterThan(productId, 0);
        Map<String, Integer> stockInfo = new HashMap<>();
        for (Stock stock : stocks) {
            stockInfo.put(stock.getLocation().getCountry(), stock.getQuantity());
        }
        stockDto.setStockInfo(stockInfo);
        return stockDto;

    }

}
