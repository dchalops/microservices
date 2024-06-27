package com.auth.jwtserver.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "samples")
public class PatientSample {
    @Id
    private String id;
    private String patientName;
    private String testName;
    private String doctorRequest;
    private String department;

    // Getters y Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getDoctorRequest() {
        return doctorRequest;
    }

    public void setDoctorRequest(String doctorRequest) {
        this.doctorRequest = doctorRequest;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
