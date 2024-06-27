package com.microservice.sampleservice;

import com.microservice.sampleservice.model.Sample;
import com.microservice.sampleservice.repository.SampleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class SampleServiceApplication implements CommandLineRunner {
    private final SampleRepository sampleRepository;

    public SampleServiceApplication(SampleRepository sampleRepository) {
        this.sampleRepository = sampleRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SampleServiceApplication.class, args);
    }

    @Override
    public void run(String... args) {
        var sample = Sample.builder()
                .patientId(1)
                .testType("Urine Test")
                .doctorInstructions("Morning sample")
                .departmentId(2)
                .barcode("89ABC")
                .active(true)
                .build();
        if (sampleRepository.findByPatientId(12345).isEmpty()) sampleRepository.save(sample);
    }
}
