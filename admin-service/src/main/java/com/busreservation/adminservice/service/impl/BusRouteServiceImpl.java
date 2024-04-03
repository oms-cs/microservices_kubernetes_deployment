package com.busreservation.adminservice.service.impl;

import com.busreservation.adminservice.exception.RouteAlreadyExists;
import com.busreservation.adminservice.model.BusRoute;
import com.busreservation.adminservice.repository.BusRouteRepository;
import com.busreservation.adminservice.service.BusRouteService;
import com.busreservation.adminservice.util.ObjectMapper;
import com.busreservation.adminservice.valueobjects.BusRouteVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusRouteServiceImpl implements BusRouteService {

    private static final Logger log = LogManager.getLogger(BusRouteServiceImpl.class);
    private BusRouteRepository busRouteRepository;
    public BusRouteServiceImpl(BusRouteRepository busRouteRepository){
        this.busRouteRepository = busRouteRepository;
    }
    @Override
    public BusRouteVO addRoute(BusRouteVO busRouteVO) {
        log.info("BusRouteServiceImpl : addRoute.");
        log.info("Add BusRoute {}.",busRouteVO);
        BusRoute busRoute = null;
        try {
            busRoute = busRouteRepository.save(ObjectMapper.voToEntity(busRouteVO));
        }catch(DataIntegrityViolationException e){
            log.error("Bus Route [busNumber={}, source={}, destination={}] Already Exists!!",busRouteVO.busNumber(), busRouteVO.source(), busRouteVO.destination());
            throw new RouteAlreadyExists("BusRoute [busNumber={"+busRouteVO.busNumber()+"}, source={"+busRouteVO.source()+"}, destination={"+busRouteVO.destination()+"}] Already Exists!! ");
        }
        log.info("Successfully Added BusRoute {} to Data",busRoute.getBusNumber());
        return ObjectMapper.entityToVO(busRoute);
    }

    @Override
    public BusRouteVO updateRoute(BusRouteVO busRouteVO) {
        log.info("BusRouteServiceImpl : updateRoute.");
        log.info("Update BusRoute {}.",busRouteVO);
        BusRoute getRouteData = busRouteRepository.findByBusNumber(busRouteVO.busNumber()).orElseThrow();
        log.info("Bus Route Before Update From Data = {}",getRouteData);
        BusRoute routeUpdateData = ObjectMapper.mapForUpdate(busRouteVO, getRouteData);
        var updatedRoute = ObjectMapper.entityToVO(busRouteRepository.save(routeUpdateData));
        log.info("Updated Route = {}",updatedRoute);
        return updatedRoute;
    }

    @Override
    public void deleteRoute(Long id) {
        log.info("BusRouteServiceImpl : deleteRoute.");
        log.info("Update BusRouteId {}.",id);
        busRouteRepository.deleteById(id);
        log.info("Successfully Deleted Bus Route {}.",id);
    }

    @Override
    public BusRouteVO fetchRoute(Long id) {
        log.info("BusRouteServiceImpl : fetchRoute.");
        log.info("Fetch BusRouteId {}.",id);
        BusRoute fetchedRoute = busRouteRepository.findById(id).orElseThrow();
        log.info("BusRoute Fetched for id {} = {}",id,fetchedRoute);
        return ObjectMapper.entityToVO(fetchedRoute);
    }

    @Override
    public List<BusRouteVO> fetchAllRoutes() {
        log.info("BusRouteServiceImpl : fetchAllRoutes.");
        List<BusRoute> busRouteList = busRouteRepository.findAll();
        log.info("BusRouteList = {}",busRouteList);
        return busRouteList.stream()
                .map(busRoute -> ObjectMapper.entityToVO(busRoute))
                .toList();
    }

    @Override
    public BusRouteVO fetchRoute(String busNumber) {
        log.info("BusRouteServiceImpl : fetchRoute.");
        log.info("Fetch BusRouteId {}.",busNumber);
        BusRoute fetchedRoute = busRouteRepository.findByBusNumber(busNumber).orElseThrow();
        log.info("BusRoute Fetched for busNumber {} = {}",busNumber,fetchedRoute);
        return ObjectMapper.entityToVO(fetchedRoute);
    }
}
