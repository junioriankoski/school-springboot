package com.example.escola.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(RecursosNaoEncontradosException.class)
    public ResponseEntity<String> naoEncontrado(RecursosNaoEncontradosException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }
}
