package br.com.igorcoura.employeemanagement.services.exception;

import org.springframework.dao.EmptyResultDataAccessException;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(String message){
        super(message);
    }

}

