package com.busreservation.bookingservice.repository;

import com.busreservation.bookingservice.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Integer> {
}
