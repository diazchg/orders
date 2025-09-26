package com.workia.orders.application.dto.in;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;


@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductBody {

    @NotEmpty(message = "Must provide a product name")
    private String name;

    @Min(value = 1, message = "Product Quantity must always be more than 0")
    private int quantity;

    @Min(value = 1, message = "Product unit price must always be more than 0")
    private double unitPrice;
}
