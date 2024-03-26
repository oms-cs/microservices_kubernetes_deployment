package com.busreservation.bookingservice.service.impl;

import com.busreservation.bookingservice.config.BusRouteClient;
import com.busreservation.bookingservice.config.InventoryClient;
import com.busreservation.bookingservice.exception.NotFoundException;
import com.busreservation.bookingservice.model.Booking;
import com.busreservation.bookingservice.model.BookingStatus;
import com.busreservation.bookingservice.model.Passenger;
import com.busreservation.bookingservice.model.PaymentStatus;
import com.busreservation.bookingservice.model.dto.BusInventoryDto;
import com.busreservation.bookingservice.model.dto.InventoryMessageVO;
import com.busreservation.bookingservice.repository.BookingRepository;
import com.busreservation.bookingservice.service.BookingService;
import com.busreservation.bookingservice.util.BusApplicationUtility;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class BookingServiceImpl implements BookingService {
    private final static Logger log = LogManager.getLogger(BookingServiceImpl.class);
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BusApplicationUtility busApplicationUtility;
    @Autowired
    private InventoryClient inventoryClient;
    @Autowired
    private BusRouteClient busRouteClient;

    @Override
    @Transactional
    public Booking createReservation(Booking booking) {
        log.info("BookingServiceImpl : createReservation");
        log.info("Book Seat with Details : {}",booking);
        String busNumber = booking.getBusNumber();
        //call Inventory Service to Get AvailableSeats
        BusInventoryDto busInventoryDto = inventoryClient.getAvailableSeats(busNumber);

        Double fare = busRouteClient.getBusRoute(booking.getBusNumber()).fare();
        log.info("Inventory Details for Bus {}, are {}", busNumber, busInventoryDto);
        if(booking.getNumberOfSeats() > busInventoryDto.getAvailableSeats()){
            log.error("The Booking is Closed for Bus {}",busNumber);
        }
        //Initialize Passengers of Booking.
        Set<Passenger> passengerList = new HashSet<>();
        for(int seat = 0; seat < booking.getNumberOfSeats(); seat++){
            Passenger passenger = new Passenger();
            passenger.setBooking(booking);
            passengerList.add(passenger);
        }
        //Booking Date
        booking.setBookingDate(LocalDateTime.now());
        // Set Passengers.
        booking.setPassengers(passengerList);
        //Initialize Booking in Table with Status Pending.
        booking.setStatus(BookingStatus.PENDING);
        Booking savedBooking = bookingRepository.save(booking);
        log.info("Booking Saved = {}",savedBooking);
        //if success -> produce a Event to Kafka Topic - Book Seat
        busApplicationUtility.sendBookingInitiationMessage(booking, fare);
        return savedBooking;
    }

    @Override
    public void deleteBooking(String bookingNumber) {
        log.info("BookingServiceImpl : deleteReservation");
        log.info("Book Seat with Details : {}",bookingNumber);
        bookingRepository.deleteById(bookingNumber);
        log.info("Booking {} Deleted ",bookingNumber);
    }

    @Override
    public List<Booking> fetchAllBooking() {
        log.info("BookingServiceImpl : findALlReservations");
        return bookingRepository.findAll();
    }

    @Override
    public Booking fetchBooking(String bookingNumber) {
        log.info("BookingServiceImpl : fetchReservation");
        log.info("BookingServiceImpl : fetchReservation bookingNumber == {}",bookingNumber);
        return bookingRepository.findById(bookingNumber).orElseThrow();
    }

    @Override
    public Booking updateBooking(Booking booking) {
        log.info("BookingServiceImpl : fetchReservation");
        log.info("BookingServiceImpl : fetchReservation booking == {}",booking);
        return bookingRepository.save(booking);
    }


    @KafkaListener(topics = "${com.bus.reservation.mq.inventory.topic-name}", groupId = "${spring.kafka.consumer.group-id}")
    public void updateBookingDataPostInventoryDeduction(InventoryMessageVO inventoryMessageVO) {
        log.info("BusBookingServiceImpl : updateBookingDataPostInventoryDeduction");
        log.info("updateInventoryPostPayment BusInventoryDto = ",inventoryMessageVO);

        var booking = bookingRepository.findById(inventoryMessageVO.getBookingNumber());
        log.info("fetched booking to update status {}",booking);
        if(!(booking.isPresent())){
            throw new NotFoundException("Booking "+inventoryMessageVO.getBookingNumber()+" Not Found!!");
        }
        var updateBooking = booking.get();
        switch (inventoryMessageVO.getPaymentStatus()){
            case SUCCESS -> {
                updateBooking.setStatus(BookingStatus.SUCCESS);
                break;
            }
            case PENDING -> {
                updateBooking.setStatus(BookingStatus.PENDING);
                break;
            }
            case FAILED -> {
                updateBooking.setStatus(BookingStatus.FAILED);
                break;
            }
            default -> {
                updateBooking.setStatus(BookingStatus.HOLD);
                break;
            }
        }
        var updatedBooking = bookingRepository.save(updateBooking);

        log.info("Booking Updated After Successful Payment Processed, Publishing The status for Booking Service to Read. {}",updatedBooking);

    }

}
