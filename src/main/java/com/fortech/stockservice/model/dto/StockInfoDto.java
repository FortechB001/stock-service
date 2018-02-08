package com.fortech.stockservice.model.dto;

import lombok.Data;

import java.util.Map;

/**
 * Dto for sending the information about stock location and quantities for one product id.
 */
@Data
public class StockInfoDto {

    /**
     * The product id.
     */
    private String productId;

    /**
     * The total stock available.
     */
    private Integer stockTotal;
}
