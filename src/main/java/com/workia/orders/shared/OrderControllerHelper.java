package com.workia.orders.shared;

import com.workia.orders.application.dto.in.ProductBody;
import com.workia.orders.domain.model.Product;

public class OrderControllerHelper {

    public static Product wrapFromProductRequest(ProductBody productBody){
        return Product.builder()
                .name(productBody.getName())
                .quantity(productBody.getQuantity())
                .unitPrice(productBody.getUnitPrice())
                .build();
    }
}
