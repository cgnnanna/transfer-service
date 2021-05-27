package com.example.transfer.service.exception;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String message){
        super(message);
    }

    public BadRequestException(String message, Throwable throwable){
        super(message, throwable);
    }
}
