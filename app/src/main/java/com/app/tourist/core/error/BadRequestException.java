package com.app.tourist.core.error;

public class BadRequestException extends Exception{

    public BadRequestException(String message){
        super(message);
    }
}
