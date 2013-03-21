package org.motechproject.whp.reports.contract.query;

import lombok.Data;

@Data
public class PatientAdherenceInfo {
    private String patientId;
    private String mobileNumber;
    private int missingWeeks;

    public PatientAdherenceInfo() {
    }

    public PatientAdherenceInfo(String patientId, String mobileNumber, int missingWeeks) {
        this.patientId = patientId;
        this.mobileNumber = mobileNumber;
        this.missingWeeks = missingWeeks;
    }
}
