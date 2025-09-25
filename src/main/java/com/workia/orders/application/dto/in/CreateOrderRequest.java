package com.workia.orders.application.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;


@Getter
@Builder
@AllArgsConstructor
public class CreateOrderRequest {

    private ClientBody client;
    private List<ProductBody> products;
}
