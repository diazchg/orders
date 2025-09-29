package com.workia.orders.application.usecases;

import com.workia.orders.application.ports.in.CreateOrderUseCase;
import com.workia.orders.application.ports.out.OrderRepositoryPort;
import com.workia.orders.domain.model.CalculatedOrder;
import com.workia.orders.domain.model.CreatedOrder;
import com.workia.orders.domain.model.Order;
import com.workia.orders.domain.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class CreateOrderUseCaseImpl implements CreateOrderUseCase {

    private final OrderService orderService;
    private final OrderRepositoryPort orderRepositoryPort;

    @Override
    public CreatedOrder execute(Order order) {
        CalculatedOrder calculatedOrder = this.orderService.calculateOrderTotal(order);
        CreatedOrder createdOrder = orderRepositoryPort.saveOrder(calculatedOrder);
        return CreatedOrder.builder()
                .creationDate(createdOrder.getCreationDate())
                .uuid(createdOrder.getUuid())
                .totalAmount(createdOrder.getTotalAmount())
                .build();
    }
}
