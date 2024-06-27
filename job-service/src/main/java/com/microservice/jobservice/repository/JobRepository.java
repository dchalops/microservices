package com.microservice.jobservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.jobservice.model.Job;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, String> {
    List<Job> getJobsByCategoryId(String id);

    List<Job> getJobsByKeysContainsIgnoreCase(String key);
}
