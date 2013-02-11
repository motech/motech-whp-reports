package org.motechproject.whp.reports.contract.patient;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class TreatmentDTO implements Serializable {
    private String providerId;
    private String providerDistrict;
    private String tbId;
    private Date startDate;
    private Date endDate;

    private String treatmentOutcome;
    private String patientType;
    private String tbRegistrationNumber;
    private String preTreatmentSmearTestResult;
    private Double preTreatmentWeight;

    private String currentTreatment;

    private String isPaused;
    private Date pausedDate;
    private String reasonsForPause;
    private int totalPausedDuration;//days
}
