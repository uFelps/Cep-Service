package com.service.cep_service.controllers.exceptions;

import lombok.Getter;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ValidationError extends StandardError{

    private List<FieldMessage> errors = new ArrayList<>();

    public void addError(String field, String message){
        errors.add(new FieldMessage(field, message));
    }
}
