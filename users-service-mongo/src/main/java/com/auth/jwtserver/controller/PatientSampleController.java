package com.auth.jwtserver.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.auth.jwtserver.document.PatientSample;
import com.auth.jwtserver.service.PatientSampleService;

@RestController
@RequestMapping("/api/samples")
public class PatientSampleController {
    @Autowired
    private PatientSampleService service;

    @GetMapping
    public List<PatientSample> getAllSamples() {
        return service.getAllSamples();
    }

    @PostMapping
    public PatientSample createSample(@RequestBody PatientSample sample) {
        return service.saveSample(sample);
    }

    @PutMapping("/{id}")
    public PatientSample updateSample(@PathVariable String id, @RequestBody PatientSample sample) {
        sample.setId(id);
        return service.saveSample(sample);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSample(@PathVariable String id) {
        service.deleteSample(id);
        String response = "{\"message\": \"Sample deleted successfully\"}";
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
