package com.busreservation.bookingservice.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Entity
public class Passenger implements Serializable {
    // Passengerid, booking number

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passenger_id")
    private Integer passengerId;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Booking booking;
}
