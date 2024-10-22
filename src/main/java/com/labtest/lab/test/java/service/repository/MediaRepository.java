package com.labtest.lab.test.java.service.repository;

import com.labtest.lab.test.java.service.entity.Media;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MediaRepository extends MongoRepository<Media, String> {
    List<Media> findByStartTimeBetween(LocalDateTime startTime, LocalDateTime endTime);
}

