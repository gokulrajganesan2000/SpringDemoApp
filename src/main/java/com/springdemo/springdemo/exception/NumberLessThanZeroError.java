package com.springdemo.springdemo.exception;

public class NumberLessThanZeroError extends RuntimeException{
    public NumberLessThanZeroError(String message){
        super(message);
    }
}
