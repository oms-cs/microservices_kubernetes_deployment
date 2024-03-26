package com.busreservation.paymentservice.service;

import com.busreservation.paymentservice.valueobjects.BookingMessageVO;

public interface PaymentService {

    public void processPayment(BookingMessageVO bookingMessageVO);
}
