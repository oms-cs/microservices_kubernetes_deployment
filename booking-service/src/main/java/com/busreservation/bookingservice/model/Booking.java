package com.busreservation.bookingservice.model;

import com.busreservation.bookingservice.model.dto.BookingDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "online_reservation_details_table", indexes = @Index(name = "idx_ordt",columnList = "booking_number, bus_number"))
public class Booking implements Serializable {
    //booking number, bus number, booking date, source, destinations, number of seats, status
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "booking_number")
    private String bookingNumber;
    @Column(name = "bus_number")
    private String busNumber;

    @Column(name = "booking_date")
    private LocalDateTime bookingDate;
    @Column(name = "source")
    private String source;
    @Column(name = "destination")
    private String destination;

    @Column(name = "number_of_seats")
    private Integer numberOfSeats;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private BookingStatus status;
    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    private Set<Passenger> passengers;

    public Booking() {
    }

    public Booking(String bookingNumber, String busNumber, LocalDateTime bookingDate, String source, String destination, Integer numberOfSeats, BookingStatus status, Set<Passenger> passengers) {
        this.bookingNumber = bookingNumber;
        this.busNumber = busNumber;
        this.bookingDate = bookingDate;
        this.source = source;
        this.destination = destination;
        this.numberOfSeats = numberOfSeats;
        this.status = status;
        this.passengers = passengers;
    }

    public Booking(BookingDto booking){
        this.busNumber = booking.getBusNumber();
        this.bookingDate  = booking.getBookingDate();
        this.source = booking.getSource();
        this.destination = booking.getDestination();
        this.numberOfSeats = booking.getNumberOfSeats();
        this.status = booking.getStatus();
        this.passengers = booking.getPassengers();
    }

    public String getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(String bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestrination(String destination) {
        this.destination = destination;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Set<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(Set<Passenger> passengers) {
        this.passengers = passengers;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingNumber='" + bookingNumber + '\'' +
                ", busNumber='" + busNumber + '\'' +
                ", bookingDate=" + bookingDate +
                ", source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                ", numberOfSeats=" + numberOfSeats +
                ", status=" + status +
                ", passengers=" + passengers +
                '}';
    }
}
