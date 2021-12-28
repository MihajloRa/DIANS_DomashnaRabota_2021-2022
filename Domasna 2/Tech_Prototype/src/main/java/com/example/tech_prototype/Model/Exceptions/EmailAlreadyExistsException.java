package com.example.tech_prototype.Model.Exceptions;

public class EmailAlreadyExistsException extends RuntimeException{
    public EmailAlreadyExistsException(String email) {
        super(String.format("%s is already registered!", email));
    }
}
