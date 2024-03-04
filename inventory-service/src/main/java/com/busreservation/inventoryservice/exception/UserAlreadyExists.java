package com.busreservation.inventoryservice.exception;

public class UserAlreadyExists extends RuntimeException{

    public UserAlreadyExists(String exceptionMessage) {
        super(exceptionMessage);
    }
}
