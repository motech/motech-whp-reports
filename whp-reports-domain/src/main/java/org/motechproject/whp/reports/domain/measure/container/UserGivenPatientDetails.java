package org.motechproject.whp.reports.domain.measure.container;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Embeddable
public class UserGivenPatientDetails {
    @Column(name = "given_patient_id")
    private String patientId;

    @Column(name = "given_patient_name")
    private String patientName;

    @Column(name = "given_patient_age")
    private Integer patientAge;

    @Column(name = "given_gender")
    private String gender;
}