package com.workia.orders.infrastructure.adapter.in.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import static com.workia.orders.shared.UrlConstants.ORDERS_URL;

@RestController
@RequestMapping(value = ORDERS_URL)
public class OrderController {

    @PostMapping
    public ResponseEntity createOrder() throws Exception {
        return ResponseEntity.created(new URI("/orders/1")).build();
    }
}
