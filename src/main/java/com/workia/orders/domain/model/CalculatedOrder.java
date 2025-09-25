package com.workia.orders.domain.model;

import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class CalculatedOrder {
    private String uuid;
    private double totalAmount;

}
