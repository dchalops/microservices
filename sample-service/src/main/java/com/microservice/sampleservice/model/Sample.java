package com.microservice.sampleservice.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "samples")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Sample extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "patient_id", nullable = false)
    private Integer patientId;

    @Column(name = "test_type", nullable = false)
    private String testType;

    @Column(name = "doctor_instructions")
    private String doctorInstructions;

    @Column(name = "department_id", nullable = false)
    private Integer departmentId;

    @Column(name = "barcode", nullable = false, unique = true)
    private String barcode;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @Column(name = "creation_timestamp", nullable = false)
    private Timestamp creationTimestamp;

    @Column(name = "update_timestamp", nullable = false)
    private Timestamp updateTimestamp;

    // Métodos getters para testType y doctorInstructions
    public String getTestType() {
        return testType;
    }

    public String getDoctorInstructions() {
        return doctorInstructions;
    }
    
}
