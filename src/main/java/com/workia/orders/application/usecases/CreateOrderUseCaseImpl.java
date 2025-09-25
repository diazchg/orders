package com.workia.orders.application.usecases;

import com.workia.orders.application.ports.in.CreateOrderUseCase;
import com.workia.orders.application.ports.out.OrderRepositoryPort;
import com.workia.orders.domain.model.CalculatedOrder;
import com.workia.orders.domain.model.CreatedOrder;
import com.workia.orders.domain.model.Order;
import com.workia.orders.domain.service.OrderService;
import com.workia.orders.infrastructure.adapter.out.persistence.entity.CreatedOrderEntity;
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
        CreatedOrderEntity createdOrderEntity = orderRepositoryPort.saveOrder(calculatedOrder);
        return CreatedOrder.builder()
                .creationDate(createdOrderEntity.getCreationDate())
                .uuid(createdOrderEntity.getUuid())
                .totalAmount(createdOrderEntity.getTotalAmount())
                .build();
    }
}
