package org.motechproject.whp.reports.contract.patient;

import lombok.Data;

import java.sql.Date;

@Data
public class Treatment {
    private String providerId;
    private String providerDistrict;
    private String tbId;
    private Date startDate;
    private Date endDate;

    private String treatmentOutcome;
    private String patientType;
    private String tbRegistrationNumber;
    private String preTreatmentSmearTestResult;
    private int preTreatmentWeight;

    private String currentTreatment;

    private String isPaused;
    private Date pausedDate;
    private String reasonsForPause;
    private int totalPausedDuration;//days
}
