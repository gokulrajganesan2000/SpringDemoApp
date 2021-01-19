package com.springdemo.springdemo.exception;

public class EmailAddressNotValidException extends RuntimeException{
    public EmailAddressNotValidException(String exception){
        super(exception);
    }
}
