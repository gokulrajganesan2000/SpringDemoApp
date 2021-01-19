package com.springdemo.springdemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NameNotFoundException.class)
    public ResponseEntity<?> nameNotFoundException(NameNotFoundException exception, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailAddressNotValidException.class)
    public ResponseEntity<?> emailAddressNotValidException(EmailAddressNotValidException exception, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity(errorDetails, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(NumberLessThanZeroError.class)
    public ResponseEntity<?> numberLessThanZeroError(NumberLessThanZeroError exception, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity(errorDetails, HttpStatus.NOT_ACCEPTABLE);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException exception, WebRequest webRequest) {

        Pattern MY_PATTERN = Pattern.compile("\\[(.*?)]");
        Matcher m = MY_PATTERN.matcher(exception.getMessage());
        String s = "";
        while (m.find()) {
            s = m.group(1);
        }

        ErrorDetails errorDetails = new ErrorDetails(new Date(), s
                , webRequest.getDescription(false));
        return new ResponseEntity(errorDetails, HttpStatus.NOT_ACCEPTABLE);
    }

}
