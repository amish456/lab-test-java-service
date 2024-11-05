package com.labtest.lab.test.java.service.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.labtest.lab.test.java.service.entity.Failure;
import com.labtest.lab.test.java.service.repository.FailureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class FailureService {

    @Autowired
    private FailureRepository failureRepository;

    @Autowired
    private KafkaProducerService kafkaProducerService;

    public List<Failure> getAllFailures() {
        return failureRepository.findAll();
    }

    public Failure addFailure(Failure failure) {

        Failure response = new Failure();

        try {
            failure.setTimestamp(LocalDateTime.now());
            response = failureRepository.save(failure);

            ObjectMapper ob = new ObjectMapper();
            String event = ob.writeValueAsString(response);
            kafkaProducerService.sendLog(event);

            log.info("Failure happened and stored successfully: {}", response);

        } catch (Exception e) {
            log.error("Error in logging failure: {}", e.getMessage());
        }

        return response;
    }
}

