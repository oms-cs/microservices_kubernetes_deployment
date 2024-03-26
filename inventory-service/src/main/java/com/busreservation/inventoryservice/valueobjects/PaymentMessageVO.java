package com.busreservation.inventoryservice.valueobjects;


import java.io.Serializable;

public class PaymentMessageVO implements Serializable {

    private String bookingNumber;

    private String busNumber;

    private PaymentStatus paymentStatus;

    private Integer noOfSeats;

    public PaymentMessageVO() {
    }

    public PaymentMessageVO(String bookingNumber, String busNumber, PaymentStatus paymentStatus, Integer noOfSeats) {
        this.bookingNumber = bookingNumber;
        this.busNumber = busNumber;
        this.paymentStatus = paymentStatus;
        this.noOfSeats = noOfSeats;
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

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Integer getNoOfSeats() {
        return noOfSeats;
    }

    public void setNoOfSeats(Integer noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

    @Override
    public String toString() {
        return "PaymentMessageVO{" +
                "bookingNumber='" + bookingNumber + '\'' +
                ", busNumber='" + busNumber + '\'' +
                ", paymentStatus=" + paymentStatus +
                ", noOfSeats=" + noOfSeats +
                '}';
    }
}
