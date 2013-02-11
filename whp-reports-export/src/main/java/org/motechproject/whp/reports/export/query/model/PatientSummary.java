package org.motechproject.whp.reports.export.query.model;

import lombok.Data;

import java.util.Date;

@Data
public class PatientSummary {
    private String name;
    private String firstName;
    private String lastName;
    private Integer age;
    private String gender;
    private String patientId;
    private String tbId;
    private String providerId;
    private String village;
    private String providerDistrict;
    private String treatmentCategory;
    private Date tbRegistrationDate;
    private Date treatmentStartDate;
    private String diseaseClass;
    private String patientType;
    private Integer ipPillsTaken;
    private Integer ipTotalDoses;
    private Integer cpPillsTaken;
    private Integer cpTotalDoses;
    private String ipTreatmentProgress;
    private String cpTreatmentProgress;
    private Integer cumulativeMissedDoses;
    private String treatmentOutcome;
    private Date treatmentClosingDate;
    private String pretreatmentResult;
    private Double pretreatmentWeight;

}