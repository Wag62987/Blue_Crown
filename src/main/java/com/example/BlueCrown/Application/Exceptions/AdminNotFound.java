package com.example.BlueCrown.Application.Exceptions;

public class AdminNotFound extends RuntimeException {
    
    public AdminNotFound(String msg){
        super(msg);
    }
}
