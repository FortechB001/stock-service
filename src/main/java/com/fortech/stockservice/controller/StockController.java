package com.fortech.stockservice.controller;

import com.fortech.stockservice.model.Stock;
import com.fortech.stockservice.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockRepository stockRepository;

    @GetMapping(value = "/all")
    public List<Stock> getAll() {
        return stockRepository.findAll();
    }
}
