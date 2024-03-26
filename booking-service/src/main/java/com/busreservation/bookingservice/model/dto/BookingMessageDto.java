package com.busreservation.bookingservice.model.dto;

import com.busreservation.bookingservice.model.BookingStatus;

import java.io.Serializable;
import java.time.LocalDateTime;

public class BookingMessageDto implements Serializable {
    private String bookingNumber;
    private String busNumber;
    private LocalDateTime bookingDate;
    private BookingStatus status;

    private Integer noOfSeats;

    private Double fare;

    public BookingMessageDto() {
    }

    public BookingMessageDto(String bookingNumber, String busNumber, LocalDateTime bookingDate, Integer noOfSeats, BookingStatus status, Double fare) {
        this.bookingNumber = bookingNumber;
        this.busNumber = busNumber;
        this.bookingDate = bookingDate;
        this.noOfSeats = noOfSeats;
        this.status = status;
        this.fare = fare;
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

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public Integer getNoOfSeats() {
        return noOfSeats;
    }

    public void setNoOfSeats(Integer noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

    public Double getFare() {
        return fare;
    }

    public void setFare(Double fare) {
        this.fare = fare;
    }

    @Override
    public String toString() {
        return "BookingMessageDto{" +
                "bookingNumber='" + bookingNumber + '\'' +
                ", busNumber='" + busNumber + '\'' +
                ", bookingDate=" + bookingDate +
                ", status=" + status +
                ", noOfSeats=" + noOfSeats +
                ", fare=" + fare +
                '}';
    }
}
