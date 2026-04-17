package com.example.demo.service;

import java.io.IOException;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entity.Order1;
import com.example.demo.entity.Status;
import com.example.demo.repository.Order1Repository;

@Service
public class OrderService {
	@Autowired
	Order1Repository orderRepository;

	public Optional<Order1> getOrderById(Integer id) {
		return orderRepository.findById(id);
	}

	public void deleteOrderById(Integer id) {
		orderRepository.deleteById(id);
	}

	@Transactional(rollbackFor = Exception.class)
	public Integer addOrder(Order1 order) throws IOException {
		if (order.getOrderLines() != null) {
			order.getOrderLines().forEach(line -> line.setOrder(order));
		}
		orderRepository.save(order);
		return order.getId();
	}

	public Iterable<Order1> getOrders() {
		return orderRepository.findAll();
	}

	@Transactional
	public Order1 updateOrderStatus(Integer id, Status newStatus) {
		Order1 order = orderRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));

		Status currentStatus = order.getStatus();
		boolean isValid = false;
		if (currentStatus == Status.CREATED && newStatus == Status.IN_TRANSIT) {
			isValid = true;
		} else if (currentStatus == Status.IN_TRANSIT && newStatus == Status.DELIVERED) {
			isValid = true;
		}
		if (!isValid) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Invalid status transition from " + currentStatus + " to " + newStatus);
		}
		order.setStatus(newStatus);
		return orderRepository.save(order);
	}
}