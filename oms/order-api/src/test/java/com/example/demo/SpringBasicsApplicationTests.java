package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.controller.NoteController;
import com.example.demo.entity.Order1;
import com.example.demo.service.NoteService;

class NoteControllerTest {
	@InjectMocks
	NoteController noteController;
	@Mock
	NoteService noteService;

	@Test
	void testGetOrderById() {

	}
	@BeforeEach
	void init () {
		MockitoAnnotations.openMocks(this);
	}
	@Test
	void testGetOrder() {
		Iterable<Order1> orders1 = new ArrayList<>();
		when(noteController.getOrder()).thenReturn(orders1);
		Iterable<Order1> result = noteController.getOrder();
		assertNotNull(result);
	}

	@Test
	void testCreateOrder() throws IOException {
		// arrange
		Order1 order1 = new Order1();
		when(noteService.addOrder(order1)).thenReturn(1);
		// act
		Integer result = noteController.createOrder(order1);
		// assert
		assertNotNull(result);
		assertEquals(result, 1);
	}

}