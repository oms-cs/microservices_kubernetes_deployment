package com.busreservation.bookingservice.util;

import com.busreservation.bookingservice.model.Booking;
import com.busreservation.bookingservice.model.dto.BookingMessageDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class BusApplicationUtility {
    @Autowired
    private KafkaProducer kafkaProducer;

    private static final Logger log = LogManager.getLogger(BusApplicationUtility.class);

    public void sendBookingInitiationMessage(Booking booking, Double fare){
        log.info("BusApplicationUtility sendBookingInitiationMessage == {}",booking);
        BookingMessageDto bookingMessageDto = new BookingMessageDto(booking.getBookingNumber()
                , booking.getBusNumber()
                , booking.getBookingDate()
                , booking.getNumberOfSeats()
                , booking.getStatus()
                , fare);
        log.info("BookingServiceImpl : sendBookingInitiationMessage");
        log.info("BookingMessage = {} ",bookingMessageDto);
        kafkaProducer.sendBookingInitiatedMessage(bookingMessageDto);
        log.info("Message Published.");
    }
}
