package org.motechproject.whp.reports.contract;

import lombok.Data;

@Data
public class UserGivenPatientDetails {
    private String patientId;
    private String patientName;
    private Integer patientAge;
    private String gender;
}
