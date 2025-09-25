package com.workia.orders.domain.service;

import com.workia.orders.domain.model.CalculatedOrder;
import com.workia.orders.domain.model.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderService {
    public CalculatedOrder calculateOrderTotal(Order order){
        return CalculatedOrder.builder().build();
    }
}
