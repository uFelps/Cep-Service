package com.service.cep_service.controllers.exceptions;

public class CepClientException extends RuntimeException {

    public CepClientException(String message){
        super(message);
    }
}
