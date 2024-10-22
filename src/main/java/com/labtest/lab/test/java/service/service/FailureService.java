package com.labtest.lab.test.java.service.service;

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

    public List<Failure> getAllFailures() {
        return failureRepository.findAll();
    }

    public Failure addFailure(Failure failure) {
        failure.setTimestamp(LocalDateTime.now());
        return failureRepository.save(failure);
    }
}

