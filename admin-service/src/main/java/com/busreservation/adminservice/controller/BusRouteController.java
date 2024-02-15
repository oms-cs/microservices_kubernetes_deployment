package com.busreservation.adminservice.controller;


import com.busreservation.adminservice.service.BusRouteService;
import com.busreservation.adminservice.valueobjects.BusRouteVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/routes")
public class BusRouteController {

    private BusRouteService busRouteService;

    public BusRouteController(BusRouteService busRouteService){
        this.busRouteService = busRouteService;
    }

    public ResponseEntity addBusRoute(BusRouteVO busRouteVO){
        return null;
    }

    public String doSomething(){
        return busRouteService.doSomething();
    }


}
