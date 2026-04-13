package com.example.demo.exceptions;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleValidationExceptions(MethodArgumentNotValidException ex) {
        return "Something Went Wrong";
    }

    @ExceptionHandler(Exception.class)
    public String handleExceptions(Exception ex) {
        return "Something Went Wrong";
    }
}
