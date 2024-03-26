package com.busreservation.bookingservice.config;


import com.busreservation.bookingservice.model.dto.BusInventoryDto;
import com.busreservation.bookingservice.util.ApplicationConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = ApplicationConstants.INVENTORY_SERVICE)
public interface InventoryClient {

    @RequestMapping(method = RequestMethod.GET
            , value = ApplicationConstants.INVENTORY_SERVICE_PREFIX
            +ApplicationConstants.INVENTORY_SERVICE_AVAILABLE_SEATS_ENDPOINT)
    public BusInventoryDto getAvailableSeats(@RequestParam(name = "busNumber") String busNumber);

}
