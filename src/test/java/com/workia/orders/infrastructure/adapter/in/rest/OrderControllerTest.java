package com.workia.orders.infrastructure.adapter.in.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.workia.orders.application.dto.in.ClientBody;
import com.workia.orders.application.dto.in.CreateOrderRequest;
import com.workia.orders.application.dto.in.ProductBody;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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


    @Test
    void whenProductQuantityIsZero_thenReturnBadRequestStatusCode() throws Exception {
        CreateOrderRequest createOrderRequestBody = CreateOrderRequest.builder()
                .client(ClientBody.builder()
                        .email("gusdiaz@gmail.com")
                        .name("gustavo")
                        .build())
                .products(
                        List.of(ProductBody.builder()
                                .name("Mate")
                                .unitPrice(100)
                                .quantity(0)
                                .build())
                ).build();

        mockMvc.perform(post(new URI("/orders"))
                        .content(this.objectMapper.writeValueAsString(createOrderRequestBody))
                        .contentType(APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Product Quantity must always be more than 0")))
                .andDo(print());

    }

    @ParameterizedTest
    @MethodSource("whenARequestPropertyisNotValid_thenReturnBadRequestStatusCodeBodies")
    void whenARequestPropertyisNotValid_thenReturnBadRequestStatusCodeAndItsMessage(CreateOrderRequest createOrderRequest, String expectedMessage) throws Exception {


        mockMvc.perform(post(new URI("/orders"))
                        .content(this.objectMapper.writeValueAsString(createOrderRequest))
                        .contentType(APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString(expectedMessage)))
                .andDo(print());

    }
    private static Stream<Arguments> whenARequestPropertyisNotValid_thenReturnBadRequestStatusCodeBodies() {
        return Stream.of(
                Arguments.of(CreateOrderRequest.builder()
                        .client(ClientBody.builder().email("gusdiaz@gmail.com").name("gustavo").build())
                        .products(List.of(ProductBody.builder().name("Mate").unitPrice(100).quantity(0).build())).
                                build(),
                        "Product Quantity must always be more than 0"),
                Arguments.of(CreateOrderRequest.builder()
                                .client(ClientBody.builder().name("gustavo").build())
                                .products(List.of(ProductBody.builder().name("Mate").unitPrice(100).quantity(1).build())).
                                build(),
                        "Email can not be null"),
                Arguments.of(CreateOrderRequest.builder()
                                .client(ClientBody.builder().email("gusdiaz@gmail.com").name("gustavo").build())
                                .products(List.of(ProductBody.builder().name("Mate").unitPrice(100).quantity(1).build())).
                                build(),
                        "Client name cannot be null"),
                Arguments.of(CreateOrderRequest.builder()
                                .client(ClientBody.builder().email("gusdiaz@gmail.com").name("g").build())
                                .products(List.of(ProductBody.builder().name("Mate").unitPrice(100).quantity(1).build())).
                                build(),
                        "Must provide a valid client name"),
                Arguments.of(CreateOrderRequest.builder()
                                .client(ClientBody.builder().email("gusdiaz@gmail.com").name("gustavo").build())
                                .products(List.of()).
                                build(),
                        "Most add at least 1 Product"),
                Arguments.of(CreateOrderRequest.builder()
                                .client(ClientBody.builder().email("gusdiaz@gmail.com").name("gustavo").build())
                                .products(List.of(ProductBody.builder().name("Mate").unitPrice(100).quantity(1).build())).
                                build(),
                        "Product Quantity must always be more than 0"),
                Arguments.of(CreateOrderRequest.builder()
                                .client(ClientBody.builder().email("gusdiaz@gmail.com").name("gustavo").build())
                                .products(List.of(ProductBody.builder().name("Mate").unitPrice(0).quantity(1).build())).
                                build(),
                        "Must provide a valid price")

        );
    }
}