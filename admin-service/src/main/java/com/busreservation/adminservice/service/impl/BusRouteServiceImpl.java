package com.busreservation.adminservice.service.impl;

import com.busreservation.adminservice.model.BusRoute;
import com.busreservation.adminservice.repository.BusRouteRepository;
import com.busreservation.adminservice.service.BusRouteService;
import com.busreservation.adminservice.util.ObjectMapper;
import com.busreservation.adminservice.valueobjects.BusRouteVO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class BusRouteServiceImpl implements BusRouteService {

    private BusRouteRepository busRouteRepository;

    private final WebClient.Builder webClientBuilder;

    public BusRouteServiceImpl(BusRouteRepository busRouteRepository, WebClient.Builder webClientBuilder){
        this.busRouteRepository = busRouteRepository;
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public BusRouteVO addRoute(BusRouteVO busRouteVO) {
        BusRoute busRoute = busRouteRepository.save(ObjectMapper.voToEntity(busRouteVO));
        return ObjectMapper.entityToVO(busRoute);
    }

    @Override
    public BusRouteVO updateRoute(BusRouteVO busRouteVO) {
        BusRoute getRouteData = busRouteRepository.findByBusNumber(busRouteVO.busNumber()).orElseThrow();
        BusRoute routeUpdateData = ObjectMapper.mapForUpdate(busRouteVO, getRouteData);
        return ObjectMapper.entityToVO(busRouteRepository.save(routeUpdateData));
    }

    @Override
    public String doSomething() {
        String inventoryResponses = webClientBuilder.build().get()
                .uri("http://inventory-service/api/v1/inventory/hello")
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return inventoryResponses;
    }

    @Override
    public BusRouteVO deleteRoute(Long id) {
        return null;
    }

    @Override
    public BusRouteVO fetchRoute(Long id) {
        return null;
    }

    @Override
    public List<BusRouteVO> fetchAllRoutes() {
        return null;
    }
}
