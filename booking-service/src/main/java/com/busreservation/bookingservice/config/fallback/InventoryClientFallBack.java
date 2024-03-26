package com.busreservation.bookingservice.config.fallback;

import com.busreservation.bookingservice.config.InventoryClient;
import com.busreservation.bookingservice.model.dto.BusInventoryDto;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;

@Component
public class InventoryClientFallBack implements InventoryClient {
    @Override
    public BusInventoryDto getAvailableSeats(String busNumber) {
        return new BusInventoryDto("0", 0, Timestamp.from(Instant.now()));
    }
}
