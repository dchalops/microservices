package com.microservice.sampleservice.repository;

import com.microservice.sampleservice.model.Sample;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SampleRepository extends JpaRepository<Sample, Long> {
    Optional<Sample> findByBarcode(String barcode);
    List<Sample> findAllByActive(Boolean active);
    Optional<Sample> findByPatientId(Integer patientId);
}
