package com.workia.orders.application.dto.in;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;


@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderRequest {

    @Valid
    private ClientBody client;

    @Valid
    @NotEmpty(message = "Must provide at least 1 product")
    private List<ProductBody> products;
}
