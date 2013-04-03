package org.motechproject.whp.reports.contract;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserGivenPatientDetailsReportingRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    private String containerId;
    private String patientId;
    private String patientName;
    private Integer patientAge;
    private String gender;
}
