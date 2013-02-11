package org.motechproject.whp.reports.contract.patient;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PatientDTO implements Serializable {
    private String patientId;
    private String firstName;
    private String lastName;
    private String gender;
    private String phoneNumber;
    private String phi;
    private String patientStatus;
    private String onActiveTreatment;

    private List<TherapyDTO> therapies;
    private PatientAlertsDTO patientAlerts;
    private AddressDTO patientAddress;
}
