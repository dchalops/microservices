package com.example.ms_rest_mongo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.ms_rest_mongo.model.PatientSample;
import com.example.ms_rest_mongo.service.PatientSampleService;

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

    @DeleteMapping("/{id}")
    public void deleteSample(@PathVariable String id) {
        service.deleteSample(id);
    }
}
