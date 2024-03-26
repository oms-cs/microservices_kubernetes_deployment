package com.busreservation.bookingservice.exception;

public class UserAlreadyExists extends RuntimeException{

    public UserAlreadyExists(String exceptionMessage) {
        super(exceptionMessage);
    }
}
