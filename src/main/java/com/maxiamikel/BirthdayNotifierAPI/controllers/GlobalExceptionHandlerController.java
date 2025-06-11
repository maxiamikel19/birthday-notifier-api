package com.maxiamikel.BirthdayNotifierAPI.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.maxiamikel.BirthdayNotifierAPI.exceptions.DuplicateEntityException;
import com.maxiamikel.BirthdayNotifierAPI.exceptions.ResourceNotFoundException;
import com.maxiamikel.BirthdayNotifierAPI.exceptions.StandardError;

@ControllerAdvice
public class GlobalExceptionHandlerController {

    @ExceptionHandler(DuplicateEntityException.class)
    public ResponseEntity<StandardError> duplicateEntityException(DuplicateEntityException e) {
        StandardError err = new StandardError();
        err.setMessage(e.getMessage());
        err.setStatusCode(HttpStatus.CONFLICT.value());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFoundException(ResourceNotFoundException e) {
        StandardError err = new StandardError();
        err.setMessage(e.getMessage());
        err.setStatusCode(HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> httpMessageNotReadableException(HttpMessageNotReadableException e) {
        StandardError err = new StandardError();
        err.setMessage(
                "Fatal error! Please verify the Gender[M or F is available] value or the Date value[yyyy-MM-dd is available]");
        err.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(err);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> dataIntegrityViolationException(DataIntegrityViolationException e) {
        StandardError err = new StandardError();
        err.setMessage(e.getMessage());
        err.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
    }
}
