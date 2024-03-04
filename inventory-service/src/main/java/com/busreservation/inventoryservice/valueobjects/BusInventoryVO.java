package com.busreservation.inventoryservice.valueobjects;

import java.sql.Timestamp;

public record BusInventoryVO(Long id,
                             String busNumber,
                             Integer availableSeats,
                             Timestamp lastUpdatedTime) {
}
