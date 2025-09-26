package com.workia.orders.application.dto.in;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;


@Getter
@Builder
@ToString
@AllArgsConstructor
public class ProductBody {

    @NotEmpty(message = "Must provide a product name")
    private String name;

    @Min(value = 1, message = "Product Quantity must always be more than 0")
    private int quantity;

    @Min(value = 1, message = "Product unit price must always be more than 0")
    private double unitPrice;
}
