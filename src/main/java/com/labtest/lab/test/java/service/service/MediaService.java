package com.labtest.lab.test.java.service.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.labtest.lab.test.java.service.entity.Media;
import com.labtest.lab.test.java.service.exception.MediaNotFoundException;
import com.labtest.lab.test.java.service.repository.MediaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.Objects.nonNull;

@Service
@Slf4j
public class MediaService {

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private KafkaProducerService kafkaProducerService;

    public Resource downloadMedia(LocalDateTime startTime, LocalDateTime endTime) {
        List<Media> mediaFiles = mediaRepository.findByStartTimeBetween(startTime, endTime);

        // For simplicity, assuming we return the first media file found
        if (!mediaFiles.isEmpty()) {
            Path file = Paths.get(mediaFiles.get(0).getFilePath());
            return new FileSystemResource(file);
        }

        throw new MediaNotFoundException("No media found in the specified time range");
    }

    public Media addMedia(Media media) {

        Media response = new Media();

        try {
            media.setStartTime(LocalDateTime.now());
            if (media.getDuration() > 0) {
                media.setEndTime(media.getStartTime().plusSeconds(media.getDuration()));
            } else {
                media.setEndTime(media.getStartTime());
            }
            response = mediaRepository.save(media);
            ObjectMapper ob = new ObjectMapper();
            String event = ob.writeValueAsString(response);
            kafkaProducerService.sendLog(event);
            log.info("Media added successfully: {}", media);
        } catch (Exception e) {
            log.error("Error in logging media evidence: {}", e.getMessage());
        }
        return response;

    }
}

