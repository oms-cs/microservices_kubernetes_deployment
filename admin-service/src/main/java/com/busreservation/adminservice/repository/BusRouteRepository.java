package com.busreservation.adminservice.repository;

import com.busreservation.adminservice.model.BusRoute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BusRouteRepository extends JpaRepository<BusRoute, Long> {

    Optional<BusRoute> findByBusNumber(String busNumber);
}
