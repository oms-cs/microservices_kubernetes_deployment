package com.busreservation.inventoryservice.repository;

import com.busreservation.inventoryservice.model.BusInventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BusInventoryRepository extends JpaRepository<BusInventory, Long> {


    public Optional<BusInventory> findByBusNumber(String busNumber);
}
