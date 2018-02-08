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
     * The map of stock information with locations and quantities available in those locations.
     */
    private Integer stockInfo;
}
