package com.microservice.sampleservice.service;

import com.microservice.sampleservice.model.Sample;
import com.microservice.sampleservice.repository.SampleRepository;
import com.microservice.sampleservice.request.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SampleService {
    private final SampleRepository sampleRepository;
    private final ModelMapper modelMapper;

    public Sample saveSample(RegisterRequest request) {
        Sample toSave = Sample.builder()
                .patientId(request.getPatientId())
                .testType(request.getTestType())
                .doctorInstructions(request.getDoctorInstructions())
                .departmentId(request.getDepartmentId())
                .barcode(request.getBarcode())
                .active(true)
                .build();
        return sampleRepository.save(toSave);
    }

    public List<Sample> getAll() {
        return sampleRepository.findAllByActive(true);
    }

    public Sample getSampleById(Long id) {
        return findSampleById(id);
    }

    public Sample getSampleByBarcode(String barcode) {
        return findSampleByBarcode(barcode);
    }

    public Sample updateSampleById(Long id, RegisterRequest request) {
        Sample toUpdate = findSampleById(id);
        
        toUpdate.setPatientId(request.getPatientId());
        toUpdate.setTestType(request.getTestType());
        toUpdate.setDoctorInstructions(request.getDoctorInstructions());
        toUpdate.setDepartmentId(request.getDepartmentId());
        toUpdate.setBarcode(request.getBarcode());

        return sampleRepository.save(toUpdate);
    }

    public void deleteSampleById(Long id) {
        Sample toDelete = findSampleById(id);
        toDelete.setActive(false);
        sampleRepository.save(toDelete);
    }

    protected Sample findSampleById(Long id) {
        return sampleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sample not found"));
    }

    protected Sample findSampleByBarcode(String barcode) {
        return sampleRepository.findByBarcode(barcode)
                .orElseThrow(() -> new RuntimeException("Sample not found"));
    }
}
