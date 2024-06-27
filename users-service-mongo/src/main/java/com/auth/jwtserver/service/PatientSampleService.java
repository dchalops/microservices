package com.auth.jwtserver.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.auth.jwtserver.document.PatientSample;
import com.auth.jwtserver.repository.PatientSampleRepository;

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
