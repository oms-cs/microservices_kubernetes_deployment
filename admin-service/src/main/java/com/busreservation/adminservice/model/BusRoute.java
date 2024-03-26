package com.busreservation.adminservice.model;

import jakarta.persistence.*;

@Entity
@Table(name = "bus_route_details_table", indexes = @Index(name = "brdt_idx", unique = true, columnList = "bus_number"))
public class BusRoute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bus_number")
    private String busNumber;

    @Column(name = "source")
    private String source; // keeping this as String for now, should use as Location having Many to One Mapping

    @Column(name = "destination")
    private String destination; // keeping this as String for now, should use as Location having Many to One Mapping

    @Column(name = "fare")
    private Double fare;

    public BusRoute(){}

    public BusRoute(String busNumber, String source, String destination, Double fare){
        this.busNumber = busNumber;
        this.source = source;
        this.destination = destination;
        this.fare = fare;
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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Double getFare() {
        return fare;
    }

    public void setFare(Double fare) {
        this.fare = fare;
    }

}
