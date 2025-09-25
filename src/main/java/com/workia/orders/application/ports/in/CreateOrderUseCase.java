package com.workia.orders.application.ports.in;

import com.workia.orders.domain.model.Order;

public interface CreateOrderUseCase {
    long execute(Order order);
}
