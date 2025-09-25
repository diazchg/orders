package com.workia.orders.application.usecases;

import com.workia.orders.application.ports.in.CreateOrderUseCase;
import com.workia.orders.domain.model.Order;
import com.workia.orders.domain.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class CreateOrderUseCaseImpl implements CreateOrderUseCase {

    private final OrderService orderService;

    @Override
    public long execute(Order order) {
        return this.orderService.calculateOrderTotal(order);
    }
}
