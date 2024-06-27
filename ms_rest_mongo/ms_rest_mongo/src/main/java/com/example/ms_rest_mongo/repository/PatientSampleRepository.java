package com.example.ms_rest_mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.ms_rest_mongo.model.PatientSample;

public interface PatientSampleRepository extends MongoRepository<PatientSample, String> {
}
