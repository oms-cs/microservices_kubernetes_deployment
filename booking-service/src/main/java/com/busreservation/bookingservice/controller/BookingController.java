package com.busreservation.bookingservice.controller;

import com.busreservation.bookingservice.model.Booking;
import com.busreservation.bookingservice.model.dto.BookingDto;
import com.busreservation.bookingservice.service.BookingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/booking")
public class BookingController {

    private static final Logger log = LogManager.getLogger(BookingController.class);
    @Autowired
    private BookingService bookingService;

    @PostMapping(value = "/bookSeat")
    public ResponseEntity bookASeat(@RequestBody BookingDto bookingDto){
        log.info("BookingController : bookASeat");
        log.info("BookSeat Booking : {}",bookingDto);
        var bookingDetails = bookingService.createReservation(new Booking(bookingDto));
        log.info("BookSeat Booking : {}",bookingDto);
        return ResponseEntity.ok(new BookingDto(bookingDetails));
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity getAllBookings(){
        log.info("BookingController : bookASeat");
        var bookingDetails = bookingService.fetchAllBooking();
        log.info("All Bookings : {}",bookingDetails);
        return ResponseEntity.ok(bookingDetails.stream().map(BookingDto::new).toList());
    }

}
