package com.workia.orders.infrastructure.adapter.in.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.workia.orders.application.dto.in.ClientBody;
import com.workia.orders.application.dto.in.CreateOrderRequest;
import com.workia.orders.application.dto.in.ProductBody;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.net.URI;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.matchesRegex;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
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
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("creationDate")))
                .andExpect(content().string(containsString("uuid")))
                .andExpect(content().string(containsString("totalAmount")))
        ;



    }
}