package com.workia.orders.application.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;


@Getter
@Builder
@ToString
@AllArgsConstructor
public class ProductBody {
    private String name;
    private int amount;
    private double unitPrice;
}
