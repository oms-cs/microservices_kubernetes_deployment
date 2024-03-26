package com.busreservation.bookingservice.model.dto;

import com.busreservation.bookingservice.model.PaymentStatus;
import java.io.Serializable;

public class InventoryMessageVO implements Serializable {
    private String bookingNumber;

    private String busNumber;

    private PaymentStatus paymentStatus;

    public InventoryMessageVO() {
    }

    public InventoryMessageVO(String bookingNumber, String busNumber, PaymentStatus paymentStatus) {
        this.bookingNumber = bookingNumber;
        this.busNumber = busNumber;
        this.paymentStatus = paymentStatus;
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

    @Override
    public String toString() {
        return "InventoryMessageVO{" +
                "bookingNumber='" + bookingNumber + '\'' +
                ", busNumber='" + busNumber + '\'' +
                ", paymentStatus=" + paymentStatus +
                '}';
    }
}
