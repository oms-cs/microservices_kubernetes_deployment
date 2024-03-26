package com.busreservation.bookingservice.model.dto;

import java.sql.Timestamp;

public class BusInventoryDto {
    private String busNumber;
    private Integer availableSeats;
    private Timestamp lastUpdatedTime;

    public BusInventoryDto() {
    }

    public BusInventoryDto(String busNumber, Integer availableSeats, Timestamp lastUpdatedTime) {
        this.busNumber = busNumber;
        this.availableSeats = availableSeats;
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }

    public Timestamp getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(Timestamp lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    @Override
    public String toString() {
        return "BusInventoryDto{" +
                "busNumber='" + busNumber + '\'' +
                ", availableSeats=" + availableSeats +
                ", lastUpdatedTime=" + lastUpdatedTime +
                '}';
    }
}
