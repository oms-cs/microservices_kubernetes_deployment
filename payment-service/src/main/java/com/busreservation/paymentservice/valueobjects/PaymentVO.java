package com.busreservation.paymentservice.valueobjects;

import com.busreservation.paymentservice.model.Payment;
import com.busreservation.paymentservice.model.PaymentStatus;

import java.time.LocalDateTime;

public class PaymentVO {
    private String bookingNumber;
    private LocalDateTime paymentDate;
    private PaymentStatus status;

    private Double amount;

    public PaymentVO() {
    }

    public PaymentVO(String bookingNumber, LocalDateTime paymentDate, PaymentStatus status, Double amount) {
        this.bookingNumber = bookingNumber;
        this.paymentDate = paymentDate;
        this.status = status;
        this.amount = amount;
    }

    public PaymentVO(Payment payment){
        this.bookingNumber = payment.getBookingNumber();
        this.status = payment.getStatus();
        this.paymentDate = payment.getPaymentDate();
        this.amount = payment.getAmount();
    }

    public String getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(String bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "PaymentVO{" +
                "bookingNumber='" + bookingNumber + '\'' +
                ", paymentDate=" + paymentDate +
                ", status=" + status +
                ", amount=" + amount +
                '}';
    }
}
