package com.fortech.stockservice.controller;

import com.fortech.stockservice.model.Stock;
import com.fortech.stockservice.model.dto.StockInfoDto;
import com.fortech.stockservice.repository.StockRepository;
import com.fortech.stockservice.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest Controller for the Stock related requests.
 */
@CrossOrigin(origins = {"http://localhost:8080"})
@RestController
@RequestMapping("/stock")
public class StockController {

    /**
     * StockRepository object.
     */
    @Autowired
    private StockRepository stockRepository;

    /**
     * StockService object.
     */
    @Autowired
    private StockService stockService;


    /**
     * Displays all stocks.
     *
     * @return - the list of all stocks.
     */
    @GetMapping(value = "/all")
    public List<Stock> getAll() {
        return stockRepository.findAll();
    }

    /**
     * Finds all the stock with quantity > 0 for a given productId.
     *
     * @param productId - the product id.
     * @return - the list of stock.
     */
    @GetMapping(value = "/find")
    public StockInfoDto getStockInfoByProductId(@RequestParam(name = "productId") String productId) {
        return stockService.getStockInfoByProductId(productId);
    }

    /**
     * Adds a new stock.
     *
     * @param productId - the productId
     * @param location  - the location.
     * @param quantity  - the quantity.
     */
    @PostMapping("/add")
    public void addStock(@RequestParam(name = "productId") String productId,
                         @RequestParam(name = "location") String location,
                         @RequestParam(name = "quantity") Integer quantity) {
        stockService.addStock(productId, location, quantity);
    }

    @GetMapping(value = "/when")
    public Integer getDaysToWarehouse(@RequestParam(name = "productId") String productId,
                                      @RequestParam(name = "howMany") Integer howMany) {
        return stockService.getDaysToWarehouse(productId, howMany);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<?> removeFromStock(@RequestParam(name = "productId") String productId,
                                             @RequestParam(name = "howMany") Integer howMany) {

        boolean removed = stockService.removeFromStock(productId, howMany);
        if (removed) {
            return ResponseEntity.accepted().build();
        }
        return ResponseEntity.notFound().build();
    }
}
