package org.motechproject.whp.reports.contract.patient;

import lombok.Data;

import java.util.List;

@Data
public class Patient {
    private String patientId;
    private String firstName;
    private String lastName;
    private String gender;
    private String phoneNumber;
    private String phi;
    private String patientStatus;
    private String onActiveTreatment;

    private List<Therapy> therapies;
    private PatientAlerts patientAlerts;
    private Address patientAddress;
}
