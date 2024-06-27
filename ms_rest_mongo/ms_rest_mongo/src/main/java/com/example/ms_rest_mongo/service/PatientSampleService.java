package com.example.ms_rest_mongo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.ms_rest_mongo.model.PatientSample;
import com.example.ms_rest_mongo.repository.PatientSampleRepository;

@Service
public class PatientSampleService {
    @Autowired
    private PatientSampleRepository repository;

    public List<PatientSample> getAllSamples() {
        return repository.findAll();
    }

    public PatientSample saveSample(PatientSample sample) {
        return repository.save(sample);
    }

    public void deleteSample(String id) {
        repository.deleteById(id);
    }
}
