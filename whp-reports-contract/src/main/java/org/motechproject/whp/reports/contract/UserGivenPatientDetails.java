package org.motechproject.whp.reports.contract;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserGivenPatientDetails implements Serializable {
    private String patientId;
    private String patientName;
    private Integer patientAge;
    private String gender;
}
