package com.workia.orders.application.usecases;

import com.workia.orders.application.ports.in.CreateOrderUseCase;
import com.workia.orders.domain.model.Order;
import org.springframework.stereotype.Component;

@Component
public class CreateOrderUseCaseImpl implements CreateOrderUseCase {

    @Override
    public long execute(Order order) {
        return 1;
    }
}
