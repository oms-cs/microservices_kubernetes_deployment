package com.busreservation.inventoryservice.service;

import com.busreservation.inventoryservice.model.BusInventory;
import com.busreservation.inventoryservice.model.dto.BusInventoryDto;
import com.busreservation.inventoryservice.valueobjects.PaymentMessageVO;

import java.util.List;

public interface BusInventoryService {

    BusInventory addBusInventory(BusInventory busInventory);

    BusInventory updateInventory(BusInventory busInventory);

    void deleteInventory(Long Id);

    BusInventoryDto getAvailableSeatsByBusNumber(String busNumber);

    BusInventory getBus(Long id);

    List<BusInventory> getAllBuses();

    void updateInventoryPostPayment(PaymentMessageVO paymentMessageVO);
}
