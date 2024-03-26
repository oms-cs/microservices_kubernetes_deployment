package com.busreservation.bookingservice.model.dto;

import com.busreservation.bookingservice.model.Booking;

public class PassengerDto {
    private Integer passengerId;

    private Booking booking;

    public PassengerDto() {
    }

    public PassengerDto(Integer passengerId, Booking booking) {
        this.passengerId = passengerId;
        this.booking = booking;
    }

    public Integer getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(Integer passengerId) {
        this.passengerId = passengerId;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    @Override
    public String toString() {
        return "PassengerDto{" +
                "passengerId=" + passengerId +
                ", booking=" + booking +
                '}';
    }
}
