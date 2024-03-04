package com.busreservation.inventoryservice.model;

import com.busreservation.inventoryservice.model.dto.BusInventoryDto;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "bus_inventory_details_table"
        , indexes = @Index(name = "bidt_idx", columnList = "busNumber", unique = true))
public class BusInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bus_number")
    private String busNumber;

    @Column(name = "available_seats")
    private Integer availableSeats;

    @Column(name = "last_updated_time")
    private Timestamp lastUpdatedTime;


    public BusInventory(){
    }

    public BusInventory(Long id, String busNumber, Integer availableSeats, Timestamp lastUpdatedTime) {
        this.id = id;
        this.busNumber = busNumber;
        this.availableSeats = availableSeats;
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public BusInventory(BusInventoryDto busInventory){
        this.busNumber = busInventory.getBusNumber();
        this.availableSeats = busInventory.getAvailableSeats();
        this.lastUpdatedTime = Timestamp.valueOf(LocalDateTime.now());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

}
