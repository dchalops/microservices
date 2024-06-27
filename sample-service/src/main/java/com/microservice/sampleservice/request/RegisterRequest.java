package com.microservice.sampleservice.request;

import lombok.Data;

@Data
public class RegisterRequest {
    private Integer patientId;
    private String testType;
    private String doctorInstructions;
    private Integer departmentId;
    private String barcode;
}
