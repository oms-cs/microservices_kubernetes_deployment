package com.busreservation.bookingservice.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Passenger implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passenger_id")
    private Integer passengerId;

    @ManyToOne(cascade = CascadeType.REFRESH)

    private Booking booking;

    public Passenger(Integer passengerId, Booking booking) {
        this.passengerId = passengerId;
        this.booking = booking;
    }

    public Passenger() {
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
        return "Passenger{" +
                "passengerId=" + passengerId +
                ", booking=" + booking +
                '}';
    }
}
