package com.workia.orders.application.ports.in;

import com.workia.orders.domain.model.CreatedOrder;
import com.workia.orders.domain.model.Order;

public interface CreateOrderUseCase {
    CreatedOrder execute(Order order);
}
