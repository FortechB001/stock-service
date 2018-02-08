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
        DE("Deutchland", 3),
        UK("United Kingdom", 4),
        FR("France", 3),
        BE("Belgium", 3),
        HU("Hungary", 2),
        RO("Romania", 1);

        private String country;
        private int distance;
    }
}
