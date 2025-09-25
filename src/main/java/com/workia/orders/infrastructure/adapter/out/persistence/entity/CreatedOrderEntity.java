package com.workia.orders.infrastructure.adapter.out.persistence.entity;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class CreatedOrderEntity {
    private String uuid;
    private double totalAmount;
    private LocalDateTime creationDate;
}
