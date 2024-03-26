package com.busreservation.bookingservice.exception;

public class NoRecordsFoundException extends RuntimeException{

    public NoRecordsFoundException(){
        super();
    }

    public NoRecordsFoundException(String message){
        super(message);
    }
}
