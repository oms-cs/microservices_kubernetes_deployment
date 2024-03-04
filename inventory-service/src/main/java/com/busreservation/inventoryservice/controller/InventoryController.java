package com.busreservation.inventoryservice.controller;


import com.busreservation.inventoryservice.model.BusInventory;
import com.busreservation.inventoryservice.model.dto.BusInventoryDto;
import com.busreservation.inventoryservice.service.BusInventoryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {
    private static final Logger log = LogManager.getLogger(InventoryController.class);

    @Autowired
    private BusInventoryService busInventoryService;

    @GetMapping("/avail")
    public ResponseEntity getAvailableSeats(@RequestParam(name = "busNumber") String busNumber){
        log.info("User {} Accessed /avail Endpoint ","Anonymous");
        return ResponseEntity.ok().body(busInventoryService.getAvailableSeatsByBusNumber(busNumber));
    }

    @PostMapping("/add")
    public ResponseEntity addInventory(@RequestBody BusInventoryDto busInventoryDto){
        log.info("User {} Accessed /add Endpoint ","Anonymous");
        log.info("Add Bus to Inventory :: Data = {}",busInventoryDto);
        return ResponseEntity.ok()
                .body(new BusInventoryDto(
                    busInventoryService.addBusInventory(new BusInventory(busInventoryDto)))
                );
    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateInventory(@RequestBody BusInventoryDto busInventoryDto){
        log.info("User {} Accessed /update Endpoint ","Anonymous");
        log.info("Update Bus to Inventory :: Data = {}",busInventoryDto);
        return ResponseEntity.ok()
                .body(new BusInventoryDto(
                        busInventoryService.updateInventory(new BusInventory(busInventoryDto)))
                );
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity deleteInventory(@PathVariable Long id){
        log.info("User {} Accessed /delete/{} Endpoint ","Anonymous",id);
        log.info("Delete Bus to Inventory :: Data = {}",id);
        busInventoryService.deleteInventory(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getBusById(@PathVariable Long id){
        log.info("User {} Accessed /get/{} Endpoint ","Anonymous",id);
        log.info("GetBus Bus to Inventory :: Data = {}",id);
        return ResponseEntity.ok().body(
                new BusInventoryDto(busInventoryService.getBus(id)));
    }

    @GetMapping(value = "/")
    public ResponseEntity getAllBuses(){
        log.info("User {} Accessed /getAll Endpoint ","Anonymous");
        return ResponseEntity.ok().body(
                busInventoryService.getAllBuses().stream().map(BusInventoryDto::new).toList());
    }

}
