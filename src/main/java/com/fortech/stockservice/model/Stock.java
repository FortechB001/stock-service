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
        SUPPLIER_1("From Germany", 3),
        SUPPLIER_2("From United Kingdom", 4),
        SUPPLIER_3("From France", 3),
        SUPPLIER_4("From Belgium", 3),
        SUPPLIER_5("From Hungary", 2),
        SUPPLIER_6("From Romania", 1);

        private String country;
        private int distance;
    }
}
