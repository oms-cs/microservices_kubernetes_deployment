package com.busreservation.bookingservice.config;

import com.busreservation.bookingservice.config.fallback.BusRouteClientFallBack;
import com.busreservation.bookingservice.model.dto.BusInventoryDto;
import com.busreservation.bookingservice.model.dto.BusRouteVO;
import com.busreservation.bookingservice.util.ApplicationConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = ApplicationConstants.BUS_ROUTE_SERVICE, fallback = BusRouteClientFallBack.class)
public interface BusRouteClient {

    @RequestMapping(method = RequestMethod.GET, value = ApplicationConstants.BUS_ROUTE_SERVICE_PREFIX+ApplicationConstants.BUS_ROUTE_SERVICE_GET_ROUTE_DETAILS_ENDPOINT)
    public BusRouteVO getBusRoute(@RequestParam(name = "busNumber") String busNumber);
}
