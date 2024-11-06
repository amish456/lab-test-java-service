package com.labtest.lab.test.java.service.controller;

import com.labtest.lab.test.java.service.entity.Media;
import com.labtest.lab.test.java.service.entity.MediaRequest;
import com.labtest.lab.test.java.service.service.MediaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/media")
@Tag(name = "Media", description = "Operations Related to Media Logs")
public class MediaController {

    @Autowired
    private MediaService mediaService;

    @PostMapping("/download")
    @Operation(summary = "Get All the media logs.", description = "Get All the media logs based on time.")
    public ResponseEntity<Resource> downloadMedia(@RequestBody MediaRequest mediaRequest) {
        Resource file = mediaService.downloadMedia(mediaRequest.getStartTime(), mediaRequest.getEndTime());
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

    @PostMapping("/add")
    @Operation(summary = "Add new media logs.", description = "Add new media logs.")
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<Media> addMediaLog(@RequestBody Media media) {
        Media savedMedia = mediaService.addMedia(media);
        return new ResponseEntity<>(savedMedia, HttpStatus.CREATED);
    }
}

