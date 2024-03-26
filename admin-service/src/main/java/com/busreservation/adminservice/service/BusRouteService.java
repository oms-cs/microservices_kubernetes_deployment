package com.busreservation.adminservice.service;

import com.busreservation.adminservice.model.BusRoute;
import com.busreservation.adminservice.valueobjects.BusRouteVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BusRouteService {

    //fetch one, fetch all, add, update, delete
    BusRouteVO addRoute(BusRouteVO busRoute);

    BusRouteVO updateRoute(BusRouteVO busRoute);

    void deleteRoute(Long id);

    BusRouteVO fetchRoute(Long id);
    BusRouteVO fetchRoute(String busNumber);

    List<BusRouteVO> fetchAllRoutes();
}
