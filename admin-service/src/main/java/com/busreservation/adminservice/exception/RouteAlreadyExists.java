package com.busreservation.adminservice.exception;

public class RouteAlreadyExists extends RuntimeException{

    public RouteAlreadyExists(String exceptionMessage) {
        super(exceptionMessage);
    }
}
