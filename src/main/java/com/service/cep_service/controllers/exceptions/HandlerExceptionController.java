package com.service.cep_service.controllers.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class HandlerExceptionController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> validationError(MethodArgumentNotValidException e, HttpServletRequest request){

        ValidationError error = new ValidationError();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        error.setError("Validation Error");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        error.setTimestamp(Instant.now());
        error.setStatus(status.value());

        for (FieldError err : e.getBindingResult().getFieldErrors()){
            error.addError(err.getField(), err.getDefaultMessage());
        }

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(CepClientException.class)
    public ResponseEntity<StandardError> cepClientError(CepClientException e, HttpServletRequest request){

        StandardError error = new StandardError();
        HttpStatus status = HttpStatus.SERVICE_UNAVAILABLE;
        error.setError("Cep Service not avaliable");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        error.setTimestamp(Instant.now());
        error.setStatus(status.value());

        return ResponseEntity.status(status).body(error);
    }
}
