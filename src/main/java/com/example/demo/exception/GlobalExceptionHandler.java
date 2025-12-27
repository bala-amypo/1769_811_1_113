package com.example.demo.exception;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

@ExceptionHandler(ResourceNotFoundException.class)
public ResponseEntity<String> handle(ResourceNotFoundException e){
return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
}

@ExceptionHandler(IllegalArgumentException.class)
public ResponseEntity<String> handleIllegal(IllegalArgumentException e){
return ResponseEntity.badRequest().body(e.getMessage());
}
}
