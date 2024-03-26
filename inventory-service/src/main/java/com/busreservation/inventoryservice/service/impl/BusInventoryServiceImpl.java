package com.busreservation.inventoryservice.service.impl;

import com.busreservation.inventoryservice.exception.DuplicateDataException;
import com.busreservation.inventoryservice.exception.NoRecordsFoundException;
import com.busreservation.inventoryservice.exception.NotFoundException;
import com.busreservation.inventoryservice.model.BusInventory;
import com.busreservation.inventoryservice.model.dto.BusInventoryDto;
import com.busreservation.inventoryservice.repository.BusInventoryRepository;
import com.busreservation.inventoryservice.service.BusInventoryService;
import com.busreservation.inventoryservice.util.KafkaProducer;
import com.busreservation.inventoryservice.valueobjects.InventoryMessageVO;
import com.busreservation.inventoryservice.valueobjects.PaymentMessageVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusInventoryServiceImpl implements BusInventoryService {

    private static final Logger log = LogManager.getLogger(BusInventoryServiceImpl.class);

    private BusInventoryRepository busInventoryRepository;

    private KafkaProducer kafkaProducer;

    public  BusInventoryServiceImpl(BusInventoryRepository busInventoryRepository, KafkaProducer kafkaProducer){
        this.busInventoryRepository = busInventoryRepository;
        this.kafkaProducer = kafkaProducer;
    }

    @Override
    public BusInventory addBusInventory(BusInventory busInventory){
        log.info("BusInventoryServiceImpl : addBusInventory");
        BusInventory savedBus = null;
        try {
            savedBus = busInventoryRepository.save(busInventory);
        }catch (DataIntegrityViolationException e){
//            log.error("Exception e -> {} thrown of class {}, from addBusInventory.",e.getMessage(),e.getClass());
            log.error("Exception in Persisting Bus {}",busInventory);
            throw new DuplicateDataException("Bus {"+ busInventory.getBusNumber() +"} Already Exists!!");
        }
        log.info("Bus {} saved to Bus Inventory Data Successfully",savedBus.getBusNumber());
        return savedBus;
    }

    @Override
    public BusInventory updateInventory(BusInventory busInventory) {
        log.info("BusInventoryServiceImpl : updateBus");
        log.info("updateBus BusInventoryDto = ",busInventory);
        Optional<BusInventory> savedBus = busInventoryRepository.findByBusNumber(busInventory.getBusNumber());
        if(!savedBus.isPresent()){
            log.error("Bus {} does not Exist. Failed to Updated DB",busInventory.getBusNumber());
            throw new NotFoundException("Update Failed, Bus {"+busInventory.getBusNumber()+"} does not Exist!");
        }
        log.info("Saved Bus Data = {}",savedBus);
        busInventory.setId(savedBus.get().getId());
        BusInventory updatedBus = busInventoryRepository.save(busInventory);
        return updatedBus;
    }

    @Override
    public void deleteInventory(Long Id) {
        log.info("BusInventoryServiceImpl : deleteInventory");
        Optional<BusInventory> savedBus = busInventoryRepository.findById(Id);
        if(!savedBus.isPresent()){
            log.error("Bus {} does not Exist. Failed to Updated DB",Id);
            throw new NotFoundException("Update Failed, Bus {"+Id+"} does not Exist!");
        }
        busInventoryRepository.deleteById(Id);
        log.info("Bus {} deleted Successfully.");
    }

    @Override
    public BusInventoryDto getAvailableSeatsByBusNumber(String busNumber) {
        log.info("BusInventoryServiceImpl : getAvailableSeatsByBusNumber");
        BusInventory busInventory = busInventoryRepository.findByBusNumber(busNumber).orElseThrow();
        return new BusInventoryDto(busInventory);
    }

    @Override
    public BusInventory getBus(Long id) {
        log.info("BusInventoryServiceImpl : getBus");
        BusInventory busInventory = busInventoryRepository.findById(id).orElseThrow();
        return busInventory;
    }

    @Override
    public List<BusInventory> getAllBuses() {
        log.info("BusInventoryServiceImpl : getAllBuses");
        List<BusInventory> busInventoryList = busInventoryRepository.findAll();
        if(busInventoryList.size() <= 0 || busInventoryList.isEmpty()){
            throw new NoRecordsFoundException("No Records Found!!");
        }
        log.info("busInventoryList fetched = {}",busInventoryList);
        return busInventoryList;
    }

    @Override
    @KafkaListener(topics = "${com.bus.reservation.mq.topic-name}", groupId = "${spring.kafka.consumer.group-id}")
    public void updateInventoryPostPayment(PaymentMessageVO paymentMessageVO) {
        log.info("BusInventoryServiceImpl : updateInventoryPostPayment");
        log.info("updateInventoryPostPayment BusInventoryDto = ",paymentMessageVO);
        Optional<BusInventory> savedBus = busInventoryRepository.findByBusNumber(paymentMessageVO.getBusNumber());
        if(!savedBus.isPresent()){
            log.error("Bus {} does not Exist. Failed to Updated DB",paymentMessageVO.getBusNumber());
            throw new NotFoundException("Update Failed, Bus {"+paymentMessageVO.getBusNumber()+"} does not Exist!");
        }
        log.info("Saved Bus Data = {}",savedBus);
        var updateBus = savedBus.get();
        updateBus.setAvailableSeats(updateBus.getAvailableSeats() - paymentMessageVO.getNoOfSeats());
        BusInventory updatedBus = busInventoryRepository.save(updateBus);

        log.info("Inventory Updated After Successful Payment Processed, Publishing The status for Booking Service to Read. {}",updatedBus);
        kafkaProducer
                .sendInventoryUpdatedMessage(
                        new InventoryMessageVO(
                                  paymentMessageVO.getBookingNumber()
                                , paymentMessageVO.getBusNumber()
                                , paymentMessageVO.getPaymentStatus())
                );
    }
}
