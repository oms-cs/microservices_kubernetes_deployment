package com.busreservation.paymentservice.util;

import com.busreservation.paymentservice.valueobjects.PaymentMessageVO;
import com.busreservation.paymentservice.valueobjects.PaymentVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
	
	private static final Logger log = LogManager.getLogger(KafkaProducer.class);

    @Value("${com.bus.reservation.mq.payment.topic-name}") //payment-processed
    private String kafkaTopic;

    @Autowired
    private KafkaTemplate<String, PaymentMessageVO> kafkaTemplate;

    public void sendPaymentProcessedMessage(PaymentMessageVO paymentMessageVO){
    	log.info("Producing Message Notification for Order Placed on Topic {}",kafkaTopic);
        Message<PaymentMessageVO> message = MessageBuilder
                .withPayload(paymentMessageVO)
                .setHeader(KafkaHeaders.TOPIC, kafkaTopic)
                .build();

        log.info("Message {} Sending on Kafka Topic.",message);
        kafkaTemplate.send(message);
    }
}