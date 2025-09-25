package com.workia.orders.infrastructure.adapter.in.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;

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
        mockMvc.perform(post(new URI("/orders")))
                .andExpect(status().isCreated())
                .andExpect(header().exists("location"))
                .andExpect(header().stringValues("location", "/orders/1"));
    }
}