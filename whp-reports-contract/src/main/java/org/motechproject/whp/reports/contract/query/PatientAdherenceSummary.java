package org.motechproject.whp.reports.contract.query;

import lombok.Data;

@Data
public class PatientAdherenceSummary {
    private String patientId;
    private String mobileNumber;
    private int missingWeeks;

    public PatientAdherenceSummary() {
    }

    public PatientAdherenceSummary(String patientId, String mobileNumber, int missingWeeks) {
        this.patientId = patientId;
        this.mobileNumber = mobileNumber;
        this.missingWeeks = missingWeeks;
    }
}
