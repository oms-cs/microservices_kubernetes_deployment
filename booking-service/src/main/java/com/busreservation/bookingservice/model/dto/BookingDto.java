package com.busreservation.bookingservice.model.dto;

import com.busreservation.bookingservice.model.Booking;
import com.busreservation.bookingservice.model.BookingStatus;
import com.busreservation.bookingservice.model.Passenger;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.util.Set;

public class BookingDto {

    private String busNumber;
    private LocalDateTime bookingDate;
    private String source;
    private String destination;
    private Integer numberOfSeats;
    private BookingStatus status;

    @JsonIgnore
    private Set<Passenger> passengers;


    public BookingDto() {
    }

    public BookingDto(String busNumber, LocalDateTime bookingDate, String source, String destination, Integer numberOfSeats, BookingStatus status, Set<Passenger> passengers) {
        this.busNumber = busNumber;
        this.bookingDate = bookingDate;
        this.source = source;
        this.destination = destination;
        this.numberOfSeats = numberOfSeats;
        this.status = status;
        this.passengers = passengers;
    }

    public BookingDto(Booking booking){
        this.busNumber = booking.getBusNumber();
        this.bookingDate  = booking.getBookingDate();
        this.source = booking.getSource();
        this.destination = booking.getDestination();
        this.numberOfSeats = booking.getNumberOfSeats();
        this.status = booking.getStatus();
        this.passengers = booking.getPassengers();
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

    public void setDestination(String destination) {
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

    public Set<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(Set<Passenger> passengers) {
        this.passengers = passengers;
    }

    @Override
    public String toString() {
        return "BookingDto{" +
                "busNumber='" + busNumber + '\'' +
                ", bookingDate=" + bookingDate +
                ", source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                ", numberOfSeats=" + numberOfSeats +
                ", status=" + status +
                ", passengers=" + passengers +
                '}';
    }
}
