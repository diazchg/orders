package com.workia.orders.infrastructure.adapter.in.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.workia.orders.application.dto.in.ClientBody;
import com.workia.orders.application.dto.in.CreateOrderRequest;
import com.workia.orders.application.dto.in.ProductBody;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() throws Exception {
        this.objectMapper = new ObjectMapper();

    }


    @Test
    void validOrderCreated() throws Exception {
        CreateOrderRequest createOrderRequestBody = CreateOrderRequest.builder()
                .client(ClientBody.builder()
                        .email("gusdiaz@gmail.com")
                        .name("gustavo")
                        .build())
                .products(
                        List.of(ProductBody.builder()
                                .name("Mate")
                                .unitPrice(100)
                                .quantity(3)
                                .build())
                ).build();

        mockMvc.perform(post(new URI("/orders"))
                        .content(this.objectMapper.writeValueAsString(createOrderRequestBody))
                        .contentType(APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(header().exists("location"))
                .andExpect(header().stringValues("location", "/orders/1"));
    }
}