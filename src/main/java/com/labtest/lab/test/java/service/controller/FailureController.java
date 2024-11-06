package com.labtest.lab.test.java.service.controller;


import com.labtest.lab.test.java.service.entity.Failure;
import com.labtest.lab.test.java.service.service.FailureService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/failures")
@Tag(name = "Failure", description = "Operations Related to Failure Logs")
public class FailureController {

    @Autowired
    private FailureService failureService;

    @GetMapping
    @Operation(summary = "Get All the failure logs.", description = "Get All the failure logs.")
    public ResponseEntity<List<Failure>> getFailures() {
        List<Failure> failures = failureService.getAllFailures();
        return new ResponseEntity<>(failures, HttpStatus.OK);
    }

    @PostMapping("/add")
    @Operation(summary = "Add a new failure log", description = "Create a new failure log.")
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<Failure> addFailureLog(@RequestBody Failure failure) {
        Failure savedFailure = failureService.addFailure(failure);
        return new ResponseEntity<>(savedFailure, HttpStatus.CREATED);
    }

}
