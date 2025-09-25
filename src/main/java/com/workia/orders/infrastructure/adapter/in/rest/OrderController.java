package com.workia.orders.infrastructure.adapter.in.rest;

import com.workia.orders.application.dto.in.CreateOrderRequest;
import com.workia.orders.application.ports.in.CreateOrderUseCase;
import com.workia.orders.domain.model.Client;
import com.workia.orders.domain.model.CreatedOrder;
import com.workia.orders.domain.model.Order;
import com.workia.orders.shared.OrderControllerHelper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import static com.workia.orders.shared.UrlConstants.ORDERS_URL;

@RestController
@RequestMapping(value = ORDERS_URL)
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;

    public OrderController(CreateOrderUseCase createOrderUseCase) {
        this.createOrderUseCase = createOrderUseCase;
    }

    @PostMapping
    public ResponseEntity createOrder(@RequestBody CreateOrderRequest createOrderRequest) throws Exception {
        Order order = Order.builder()
                .client(Client.builder()
                        .email(createOrderRequest.getClient().getEmail())
                        .name(createOrderRequest.getClient().getName())
                        .build())
                .products(
                        createOrderRequest.getProducts()
                                .stream()
                                .map(OrderControllerHelper::wrapFromProductRequest)
                                .toList()
                )
                .build();
        CreatedOrder createdOrder = this.createOrderUseCase.execute(order);
        return ResponseEntity.ok(createdOrder);
    }
}
