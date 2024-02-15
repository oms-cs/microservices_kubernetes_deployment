package com.busreservation.adminservice.model;

import jakarta.persistence.*;

@Entity
@Table(name = "bus_route_location_table")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "location")
    private String location;
}
