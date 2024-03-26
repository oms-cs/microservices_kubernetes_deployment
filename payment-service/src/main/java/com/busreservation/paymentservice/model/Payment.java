package com.busreservation.paymentservice.model;


import com.busreservation.paymentservice.valueobjects.PaymentVO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String paymentId;

    private String bookingNumber;

    private LocalDateTime paymentDate;

    private Double amount;

    private PaymentStatus status;

    public Payment() {
    }

    public Payment(PaymentVO paymentVO){
        this.bookingNumber = paymentVO.getBookingNumber();
        this.status = paymentVO.getStatus();
        this.paymentDate = paymentVO.getPaymentDate();
        this.amount = paymentVO.getAmount();
    }

    public Payment(String paymentId, String bookingNumber, LocalDateTime paymentDate, PaymentStatus status, Double amount) {
        this.paymentId = paymentId;
        this.bookingNumber = bookingNumber;
        this.paymentDate = paymentDate;
        this.status = status;
        this.amount = amount;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
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
        return "Payment{" +
                "paymentId='" + paymentId + '\'' +
                ", bookingNumber='" + bookingNumber + '\'' +
                ", paymentDate=" + paymentDate +
                ", amount=" + amount +
                ", status=" + status +
                '}';
    }
}
