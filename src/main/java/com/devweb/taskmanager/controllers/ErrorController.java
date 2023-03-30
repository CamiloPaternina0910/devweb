package com.devweb.taskmanager.controllers;

import com.devweb.taskmanager.dto.GeneralResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<GeneralResponse> handleMethodArgumentNotValid(ConstraintViolationException ex) {
        List<String> errorsMessages = ex.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
        return new ResponseEntity<>(GeneralResponse.builder()
                .error(true)
                .status(403)
                .data(errorsMessages)
                .build(),
                HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GeneralResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<String> errorsMessages = ex.getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
        return new ResponseEntity<>(GeneralResponse.builder()
                .error(true)
                .status(403)
                .data(errorsMessages)
                .build(),
                HttpStatus.FORBIDDEN);
    }
}
