package com.fortech.stockservice.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@RequiredArgsConstructor
public class Stock {

    @Id
    private String id;

    @NonNull
    private String productId;

    @NonNull
    private Location location;

    @NonNull
    private Integer quantity;

    @Getter
    @AllArgsConstructor
    public enum Location {
        SUPPLIER_1("From Deutchland", 3),
        SUPPLIER_2("United Kingdom", 4),
        SUPPLIER_3("France", 3),
        SUPPLIER_4("Belgium", 3),
        SUPPLIER_5("Hungary", 2),
        RO("Romania", 1);

        private String country;
        private int distance;
    }
}
