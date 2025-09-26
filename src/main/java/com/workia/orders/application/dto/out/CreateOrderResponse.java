package com.workia.orders.application.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateOrderResponse {
    private String uuid;
    private double totalAmount;
    private LocalDateTime creationDate;
}
