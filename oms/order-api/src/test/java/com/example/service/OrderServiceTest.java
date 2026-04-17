package com.example.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.entity.Order1;
import com.example.demo.entity.OrderLine;
import com.example.demo.entity.Address;
import com.example.demo.entity.Status;
import com.example.demo.repository.Order1Repository;
import com.example.demo.service.OrderService;

import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public
class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private Order1Repository orderRepository;

    private void assertOrderThrows(Order1 order, String expectedMessage) {
        RuntimeException ex = assertThrows(RuntimeException.class, () -> OrderService.addOrder(order));
        assertEquals(expectedMessage, ex.getMessage());
    }

    @Test
    void testCreateOrderWithMissingItemName() {
        Order1 order = new Order1();
        order.setStatus(Status.CREATED);
        order.setPrice(100.0);
        order.setOrderLines(Collections.singletonList(new OrderLine("", 1, 100.0)));  
        order.setAddress(new Address());  
        assertOrderThrows(order, "Item name and quantity are required");
    }

    @Test
    void testCreateOrderWithNegativePrice() {
        Order1 order = new Order1();
        order.setStatus(Status.CREATED);
        order.setItemName("Item1");
        order.setPrice(-100.0);  
        order.setOrderLines(Collections.singletonList(new OrderLine("Item1", 1, 100.0))); 
        order.setAddress(new Address()); 
        assertOrderThrows(order, "Price must be greater than or equal to 0");
    }

    @Test
    void testCreateOrderWithInvalidAddress() {
        Order1 order = new Order1();
        order.setStatus(Status.CREATED);
        order.setItemName("Item1");
        order.setPrice(100.0); 
        order.setOrderLines(Collections.singletonList(new OrderLine("Item1", 1, 100.0)));  
        order.setAddress(null);  
        assertOrderThrows(order, "Invalid address");
    }
    @Test
    void testAddOrderThrowsWhenZeroOrderLines() {
        Order1 order = new Order1();
        order.setPrice(100);
        order.setAddress(new Address()); 
        order.setOrderLines(List.of()); 

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> orderService.addOrder(order));
        assertEquals("Item name and quantity are required", ex.getMessage());
    }
    

}
