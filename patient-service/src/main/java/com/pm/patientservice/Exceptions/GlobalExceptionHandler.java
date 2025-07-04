package com.pm.patientservice.Exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException exception) {
        Map<String, String> mp = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(
                error -> mp.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(mp);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<Map<String,String>> handleEmailAlreadyExistsException(EmailAlreadyExistsException exception){
        log.warn("email exists {}",exception.getMessage());
        Map<String,String> mp = new HashMap<>();
        mp.put("message","Email already exists");
        return ResponseEntity.badRequest().body(mp);
    }

    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<Map<String,String>> handlePatientNotFoundException(PatientNotFoundException exception){
        log.warn("Patient not found {}",exception.getMessage()); //understand the significance of {}
        Map<String,String>mp = new HashMap<>();
        mp.put("message","Patient does not exist in the database");
        return ResponseEntity.badRequest().body(mp);
    }
}

