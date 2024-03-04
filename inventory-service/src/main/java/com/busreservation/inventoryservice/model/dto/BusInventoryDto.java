package com.busreservation.inventoryservice.model.dto;

import com.busreservation.inventoryservice.model.BusInventory;
import jakarta.persistence.Column;

import java.sql.Timestamp;
import java.time.LocalDateTime;

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

    public BusInventoryDto(BusInventory busInventory){
        this.busNumber = busInventory.getBusNumber();
        this.availableSeats = busInventory.getAvailableSeats();
        this.lastUpdatedTime = busInventory.getLastUpdatedTime();
//        this.lastUpdatedTime = Timestamp.valueOf(LocalDateTime.now());
    }

    public String getBusNumber() {
        return busNumber;
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
