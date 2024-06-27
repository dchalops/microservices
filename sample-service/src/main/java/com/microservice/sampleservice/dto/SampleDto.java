package com.microservice.sampleservice.dto;

import lombok.Data;

@Data
public class SampleDto {
    private Long id;
    private Integer patientId;
    private String testType;
    private String doctorInstructions;
    private Integer departmentId;
    private String barcode;
    private Boolean active;
}
