package com.auth.jwtserver.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.auth.jwtserver.document.PatientSample;

public interface PatientSampleRepository extends MongoRepository<PatientSample, String> {
}
