package com.busreservation.bookingservice.model.dto;

public record BusRouteVO(String busNumber,
                         String source,
                         String destination,
                         Double fare
                         ) {
}