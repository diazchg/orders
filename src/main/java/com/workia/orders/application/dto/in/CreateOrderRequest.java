package com.workia.orders.application.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;


@Getter
@Builder
@ToString
@AllArgsConstructor
public class CreateOrderRequest {

    private ClientBody client;
    private List<ProductBody> products;
}
