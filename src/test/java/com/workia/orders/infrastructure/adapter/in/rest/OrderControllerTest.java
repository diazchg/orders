package com.workia.orders.infrastructure.adapter.in.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.workia.orders.application.dto.in.ClientBody;
import com.workia.orders.application.dto.in.CreateOrderRequest;
import com.workia.orders.application.dto.in.ProductBody;
import com.workia.orders.application.dto.out.CreateOrderResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.net.URI;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

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
    void whenMoreThanZeroProductsAreProvided_thenMustReturnACreatedOrder() throws Exception {
        CreateOrderRequest createOrderRequestBody = CreateOrderRequest.builder()
                .client(ClientBody.builder()
                        .email("gusdiaz@gmail.com")
                        .name("gustavo")
                        .build())
                .products(
                        List.of(ProductBody.builder().name("Mate").unitPrice(100).quantity(1)
                                .build(),
                                ProductBody.builder().name("NoMate").unitPrice(155).quantity(3)
                                        .build()
                        )
                ).build();

        MvcResult mvcResult = mockMvc.perform(post(new URI("/orders"))
                        .content(this.objectMapper.writeValueAsString(createOrderRequestBody))
                        .contentType(APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String jsonResponse = mvcResult.getResponse().getContentAsString();
        CreateOrderResponse createOrderResponse = objectMapper.readValue(jsonResponse, CreateOrderResponse.class);
        assertNotNull(createOrderResponse);
        assertEquals(565.0, createOrderResponse.getTotalAmount());
        assertNotNull(createOrderResponse.getUuid());
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
                        "Must provide an email"),
                Arguments.of(CreateOrderRequest.builder()
                                .client(ClientBody.builder().email("gusdiaz@gmail.com").build())
                                .products(List.of(ProductBody.builder().name("Mate").unitPrice(100).quantity(1).build())).
                                build(),
                        "Must provide a client name"),
                Arguments.of(CreateOrderRequest.builder()
                                .client(ClientBody.builder().email("gusdiaz@gmail.com").name("g").build())
                                .products(List.of(ProductBody.builder().name("Mate").unitPrice(100).quantity(1).build())).
                                build(),
                        "Must provide a valid client name"),
                Arguments.of(CreateOrderRequest.builder()
                                .client(ClientBody.builder().email("gusdiaz@gmail.com").name("gustavo").build())
                                .products(List.of()).
                                build(),
                        "Must provide at least 1 product"),
                Arguments.of(CreateOrderRequest.builder()
                                .client(ClientBody.builder().email("gusdiaz@gmail.com").name("gustavo").build())
                                .products(List.of(ProductBody.builder().unitPrice(100).quantity(1).build())).
                                build(),
                        "Must provide a product name"),
                Arguments.of(CreateOrderRequest.builder()
                                .client(ClientBody.builder().email("gusdiaz@gmail.com").name("gustavo").build())
                                .products(List.of(ProductBody.builder().name("Mate").unitPrice(0).quantity(1).build())).
                                build(),
                        "Product unit price must always be more than 0")

        );
    }
}