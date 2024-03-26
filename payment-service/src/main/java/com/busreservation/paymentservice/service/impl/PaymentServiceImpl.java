package com.busreservation.paymentservice.service.impl;

import com.busreservation.paymentservice.model.Payment;
import com.busreservation.paymentservice.model.PaymentStatus;
import com.busreservation.paymentservice.repository.PaymentRepository;
import com.busreservation.paymentservice.service.PaymentService;
import com.busreservation.paymentservice.util.KafkaProducer;
import com.busreservation.paymentservice.valueobjects.BookingMessageVO;
import com.busreservation.paymentservice.valueobjects.PaymentMessageVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class PaymentServiceImpl implements PaymentService {

    private static final Logger log = LogManager.getLogger(PaymentServiceImpl.class);

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private KafkaProducer kafkaProducer;

    @KafkaListener(topics = "${com.bus.reservation.mq.topic-name}", groupId = "${spring.kafka.consumer.group-id}")
    public void processPayment(BookingMessageVO bookingMessageVO) {
        log.info("consumed Message {} from Kafka topic ", bookingMessageVO);
        System.out.println("Consumed Message == " + bookingMessageVO);
        /*
        * PROCESS PAYMENT BY CALLING EXTERNAL API
        * MAYBE STRIPE TEST API WHEN STORING USER DATA
        * MAYBE TAKE PAYMENT RELATED DETAILS FROM USER HIMSELF.
        * */

        Payment payment = new Payment();
        payment.setPaymentDate(LocalDateTime.now());
        payment.setAmount(bookingMessageVO.getFare());
        payment.setStatus(PaymentStatus.SUCCESS);
        payment.setBookingNumber(bookingMessageVO.getBookingNumber());
        Payment processedPayment = paymentRepository.save(payment);

        log.info("set Payment Status to Success(for now), processedPayment = {}",processedPayment);
        log.info("sending Message to Inventory Service to Proceed with Inventory Control.");

        kafkaProducer
                .sendPaymentProcessedMessage(
                        new PaymentMessageVO(bookingMessageVO.getBookingNumber()
                                , bookingMessageVO.getBusNumber()
                                , processedPayment.getStatus()
                                , bookingMessageVO.getNoOfSeats())
                );

    }
}
