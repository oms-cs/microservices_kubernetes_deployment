package com.busreservation.adminservice.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super();
    }

    public NotFoundException(String msg) {
        super(msg);
    }

}