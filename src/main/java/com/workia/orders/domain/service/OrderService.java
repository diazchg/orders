package com.workia.orders.domain.service;

import com.workia.orders.domain.model.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderService {
    public long calculateOrderTotal(Order order){
        return 1L;
    }
}
