package com.busreservation.bookingservice.service;

import com.busreservation.bookingservice.model.Booking;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BookingService {

    Booking addBooking(Booking booking);

    Booking deleteBooking(String bookingNumber);

    List<Booking> fetchAllBooking();

    Booking fetchBooking(String bookingNumber);

    Booking updateBooking(String booking);
}
