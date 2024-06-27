package com.microservice.sampleservice.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SampleUpdateRequest {
    private String id;
    private String patientId;
    private String testType;
    private String doctorInstructions;
    private String departmentId;
    private String barcode;
}
