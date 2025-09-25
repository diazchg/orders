package com.workia.orders.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class CreatedOrder {
    private String uuid;
    private double totalAmount;
    private LocalDateTime creationDate;
}
