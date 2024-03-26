package com.busreservation.bookingservice.exception;

public class DuplicateDataException extends RuntimeException {

    public DuplicateDataException() {
        super();
    }

    public DuplicateDataException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
