package com.busreservation.adminservice.util;

import com.busreservation.adminservice.model.BusRoute;
import com.busreservation.adminservice.valueobjects.BusRouteVO;

public class ObjectMapper {
    public static BusRoute voToEntity(BusRouteVO busRouteVO){
        BusRoute busRoute = new BusRoute();
        busRoute.setBusNumber(busRouteVO.busNumber());
        busRoute.setSource(busRouteVO.source());
        busRoute.setDestination(busRouteVO.destination());
        busRoute.setFare(busRouteVO.fare());
        return busRoute;
    }

    public static BusRouteVO entityToVO(BusRoute busRoute){
        return new BusRouteVO(busRoute.getBusNumber(),
                busRoute.getSource(),
                busRoute.getDestination(),
                busRoute.getFare());
    }

    public static BusRoute mapForUpdate(BusRouteVO busRouteVO, BusRoute busRouteToBeUpdated){
        if(!busRouteVO.busNumber().isBlank()){
            busRouteToBeUpdated.setBusNumber(busRouteVO.busNumber());
        }
        if(!busRouteVO.source().isBlank()){
            busRouteToBeUpdated.setSource(busRouteVO.source());
        }
        if(!busRouteVO.destination().isBlank()){
            busRouteToBeUpdated.setDestination(busRouteVO.destination());
        }
        if(busRouteVO.fare() > 0d){
            busRouteToBeUpdated.setFare(busRouteVO.fare());
        }
        return busRouteToBeUpdated;
    }

}
