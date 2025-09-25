package com.workia.orders.domain.model;

import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class Product {
    private String name;
    private int quantity;
    private double unitPrice;
}
