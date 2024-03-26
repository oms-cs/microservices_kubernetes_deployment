package com.busreservation.bookingservice.config.fallback;

import com.busreservation.bookingservice.config.BusRouteClient;
import com.busreservation.bookingservice.model.dto.BusRouteVO;
import org.springframework.stereotype.Component;

@Component
public class BusRouteClientFallBack implements BusRouteClient {

    @Override
    public BusRouteVO getBusRoute(String busNumber) {
        return new BusRouteVO("0","", "", 0.0);
    }
}
