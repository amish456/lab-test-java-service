package com.labtest.lab.test.java.service.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.topic.logs}")
    private String logTopic;

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendLog(String message) {
        log.info("Sending event for log with message: {}, to topic: {}", message, logTopic);
        kafkaTemplate.send(logTopic, message);
    }

    public void sendTransaction(String transactionType, String message) {
        log.info("Sending event for transaction with message: {}, to topic: {}, with transaction type: {}", message, logTopic, transactionType);
        kafkaTemplate.send(logTopic, transactionType, message);
    }


}
