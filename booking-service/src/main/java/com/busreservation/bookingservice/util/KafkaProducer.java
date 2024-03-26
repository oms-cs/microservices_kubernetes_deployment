package com.busreservation.bookingservice.util;

import com.busreservation.bookingservice.model.dto.BookingMessageDto;
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

    @Value("${com.bus.reservation.mq.topic-name}") //book-seat topic
    private String kafkaTopic;

    @Autowired
    private KafkaTemplate<String, BookingMessageDto> kafkaTemplate;

    public void sendBookingInitiatedMessage(BookingMessageDto bookingMessageDto){
    	log.info("Producing Message Notification for Order Placed on Topic {}",kafkaTopic);
        Message<BookingMessageDto> message = MessageBuilder
                .withPayload(bookingMessageDto)
                .setHeader(KafkaHeaders.TOPIC, kafkaTopic)
                .build();

        log.info("Message {} Sending on Kafka Topic.",message);
        kafkaTemplate.send(message);
    }
}