package com.busreservation.inventoryservice.util;

import com.busreservation.inventoryservice.valueobjects.InventoryMessageVO;
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

    @Value("${com.bus.reservation.mq.inventory.topic-name}")
    private String kafkaTopic;

    @Autowired
    private KafkaTemplate<String, InventoryMessageVO> kafkaTemplate;

    public void sendInventoryUpdatedMessage(InventoryMessageVO inventoryMessageVO){
    	log.info("Producing Message Notification for Order Placed on Topic {}",kafkaTopic);
        Message<InventoryMessageVO> message = MessageBuilder
                .withPayload(inventoryMessageVO)
                .setHeader(KafkaHeaders.TOPIC, kafkaTopic)
                .build();

        log.info("Message {} Sending on Kafka Topic.",message);
        kafkaTemplate.send(message);
    }
}