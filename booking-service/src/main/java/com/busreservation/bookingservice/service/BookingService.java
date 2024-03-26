package com.busreservation.bookingservice.service;

import com.busreservation.bookingservice.model.Booking;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BookingService {

    Booking   createReservation(Booking booking);

    void deleteBooking(String bookingNumber);

    List<Booking> fetchAllBooking();

    Booking fetchBooking(String bookingNumber);

    Booking updateBooking(Booking booking);
}
