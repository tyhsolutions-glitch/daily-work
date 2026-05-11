package com.example.demo.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.entity.Address;
import com.example.demo.entity.Order1;
import com.example.demo.entity.OrderLine;
import com.example.demo.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(OrderController.class)
public
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private OrderService orderService;

    @Autowired
    private ObjectMapper objectMapper;

    private Order1 testOrder;
    private OrderLine testOrderLine;
    private Address testAddress;

    @BeforeEach
    void setUp() {
        testAddress = new Address();
        testAddress.setStreet("123 Main St");
        testAddress.setCity("ABC City");
        testAddress.setZip("12345");

        testOrderLine = new OrderLine();
        testOrderLine.setItem("Laptop");
        testOrderLine.setPrice(999.99);
        testOrderLine.setQuantity(1);

        testOrder = new Order1();
        testOrder.setId(1);
        testOrder.setAddress(testAddress);
        testOrder.setOrderLines(List.of(testOrderLine));
        testOrder.setPrice(999.99);

        // link bidirectional relationships
        testOrderLine.setOrder(testOrder);
        testAddress.setOrder(testOrder);
    }

    @Test
    void testGetOrderByIdForExistingOrder() throws Exception {
        when(orderService.getOrderById(1)).thenReturn(Optional.of(testOrder));

        mockMvc.perform(get("/order/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.address.street").value("123 Main St"))
                .andExpect(jsonPath("$.orderLines[0].item").value("Laptop"));

        verify(orderService).getOrderById(1);
    }

    @Test
    void testGetOrderByIdForNonExistentOrder() throws Exception {
        when(orderService.getOrderById(999)).thenReturn(Optional.empty());

        mockMvc.perform(get("/order/999")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(orderService).getOrderById(999);
    }

    @Test
    void testCreateOrderForSuccessfulCreation() throws Exception {
        when(orderService.addOrder(any(Order1.class))).thenReturn((int) 1L);

        mockMvc.perform(post("/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testOrder)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));

        verify(orderService).addOrder(isA(Order1.class));
    }
}
