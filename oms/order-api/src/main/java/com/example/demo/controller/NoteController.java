package com.example.demo.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Order1;
import com.example.demo.service.NoteService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/order")
public class NoteController {

    @Autowired
    NoteService noteService;

    @GetMapping("/{id}")
    Optional<Order1> getOrderById(@PathVariable Integer id) {
        return noteService.getOrderById(id);
    }

    @DeleteMapping("/{id}")
    void deleteOrderbyId(@PathVariable Integer id) {
        noteService.deleteOrderbyId(id);
    }

    @GetMapping
    public Iterable<Order1> getOrder() {
        return noteService.getOrder();
    }

    @PostMapping
    public int createOrder(@RequestBody @Valid Order1 order1)
            throws IOException {
        return noteService.addOrder(order1);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleHttpMessageNotReadableException(
            HttpMessageNotReadableException ex) {

        return "Invalid JSON input";
    }

    class OrderResponse {

        private String message;
        private int orderId;

        public OrderResponse(String message, int orderId) {
            this.message = message;
            this.orderId = orderId;
        }

        public String getMessage() {
            return message;
        }

        public int getOrderId() {
            return orderId;
        }
    }
}
