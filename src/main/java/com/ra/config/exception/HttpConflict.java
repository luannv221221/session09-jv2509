package com.ra.config.exception;

public class HttpConflict extends RuntimeException{
    public HttpConflict(String message){
        super(message);
    }
}
