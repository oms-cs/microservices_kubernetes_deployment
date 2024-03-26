package com.busreservation.adminservice.controller;


import com.busreservation.adminservice.service.BusRouteService;
import com.busreservation.adminservice.valueobjects.BusRouteVO;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/routes")
public class BusRouteController {

    private static final Logger log = LogManager.getLogger(BusRouteController.class);

    @Autowired
    private BusRouteService busRouteService;

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addBusRoute(@RequestBody @Valid BusRouteVO busRouteVO){
        log.info("User {} Accessed /add Endpoint ","Anonymous");
        return ResponseEntity.ok().body(busRouteService.addRoute(busRouteVO));
    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateRoute(@RequestBody @Valid BusRouteVO busRouteVO){
        log.info("User {} Accessed /add Endpoint ","Anonymous");
        return ResponseEntity.ok().body(busRouteService.updateRoute(busRouteVO));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getBusRoute(@PathVariable Long id){
        log.info("User {} Accessed /add Endpoint ","Anonymous");
        return ResponseEntity.ok().body(busRouteService.fetchRoute(id));
    }

    @GetMapping(value = "/route", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getBusRoute(@RequestParam String busNumber){
        log.info("User {} Accessed /add Endpoint ","Anonymous");
        return ResponseEntity.ok().body(busRouteService.fetchRoute(busNumber));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteBusRoute(@PathVariable Long id){
        log.info("User {} Accessed /add Endpoint ","Anonymous");
        busRouteService.deleteRoute(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity fetchAllRoutes(){
        log.info("User {} Accessed /add Endpoint ","Anonymous");
        return ResponseEntity.ok().body(busRouteService.fetchAllRoutes());
    }

}
