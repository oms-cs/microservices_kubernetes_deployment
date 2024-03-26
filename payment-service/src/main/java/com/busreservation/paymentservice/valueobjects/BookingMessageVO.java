package com.busreservation.paymentservice.valueobjects;

import com.busreservation.paymentservice.model.BookingStatus;

import java.io.Serializable;
import java.time.LocalDateTime;

public class BookingMessageVO implements Serializable {
    private String bookingNumber;
    private String busNumber;
    private LocalDateTime bookingDate;
    private BookingStatus status;
    private Integer noOfSeats;
    private Double fare;


    public BookingMessageVO() {
    }

    public BookingMessageVO(String bookingNumber, String busNumber, LocalDateTime bookingDate, BookingStatus status, Integer noOfSeats, Double fare) {
        this.bookingNumber = bookingNumber;
        this.busNumber = busNumber;
        this.bookingDate = bookingDate;
        this.status = status;
        this.noOfSeats = noOfSeats;
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
        return "BookingMessageVO{" +
                "bookingNumber='" + bookingNumber + '\'' +
                ", busNumber='" + busNumber + '\'' +
                ", bookingDate=" + bookingDate +
                ", status=" + status +
                ", noOfSeats=" + noOfSeats +
                ", fare=" + fare +
                '}';
    }
}
