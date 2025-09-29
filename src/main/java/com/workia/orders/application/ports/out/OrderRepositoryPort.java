package com.workia.orders.application.ports.out;

import com.workia.orders.domain.model.CalculatedOrder;
import com.workia.orders.domain.model.CreatedOrder;

public interface OrderRepositoryPort {
    CreatedOrder saveOrder(CalculatedOrder order);
}
