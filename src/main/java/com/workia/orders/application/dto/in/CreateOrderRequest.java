package com.workia.orders.application.dto.in;

import jakarta.validation.Valid;
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

    @Valid
    private ClientBody client;

    @Valid
    private List<ProductBody> products;
}
