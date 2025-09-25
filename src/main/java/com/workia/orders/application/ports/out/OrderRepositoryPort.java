package com.workia.orders.application.ports.out;

import com.workia.orders.domain.model.CalculatedOrder;
import com.workia.orders.infrastructure.adapter.out.persistence.entity.CreatedOrderEntity;

public interface OrderRepositoryPort {
    CreatedOrderEntity saveOrder(CalculatedOrder order);
}
