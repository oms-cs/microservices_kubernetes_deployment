package com.busreservation.adminservice.valueobjects;


import com.busreservation.adminservice.model.BusRoute;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record BusRouteVO(@NotBlank(message = "Bus Number is Mandatory!") String busNumber,

                         @NotBlank(message = "Source is Mandatory!") String source,

                         @NotBlank(message = "Destination is Mandatory!") String destination,

                         @Min(value = 0l, message = "Invalid Fare, Should be Non-Negative, Non-Zero Number")
                         Double fare
                         ) {
}
