package com.labtest.lab.test.java.service.service;

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
        media.setStartTime(LocalDateTime.now());
        if (media.getDuration() > 0) {
            media.setEndTime(media.getStartTime().plusSeconds(media.getDuration()));
        } else {
            media.setEndTime(media.getStartTime());
        }

        return mediaRepository.save(media);

    }
}

