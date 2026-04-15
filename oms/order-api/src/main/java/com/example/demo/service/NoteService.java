package com.example.demo.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Order1;
import com.example.demo.entity.OrderLine;
import com.example.demo.entity.Status;
import com.example.demo.repository.Order1Repository;

@Service
public class NoteService {

    @Autowired
    Order1Repository orderRepository;

    public Iterable<Order1> getOrder() {
        return orderRepository.findAll();
    }

    public Optional<Order1> getOrderById(Integer id) {
        return orderRepository.findById(id);
    }

    public void deleteOrderbyId(Integer id) {
        orderRepository.deleteById(id);
    }

    public Integer addOrder(Order1 order1) throws IOException {

        try {
            order1.setStatus(Status.CREATED);

            if (order1.getOrderLines() != null) {
                for (OrderLine line : order1.getOrderLines()) {
                    if (line.getItem() == null || line.getQuantity() <= 0) {
                        throw new RuntimeException("Item name and quantity are required");
                    }
                    line.setOrder(order1);
                }
            }

            if (order1.getAddress() == null) {
                throw new RuntimeException("Shipping address is required");
            }

            order1.getAddress().setOrder(order1);  // Link address to order

            Order1 savedOrder = orderRepository.save(order1);

            return savedOrder.getId();

        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Error while saving order");
        }
    }
}
