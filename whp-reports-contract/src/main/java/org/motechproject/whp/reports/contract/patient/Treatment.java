package org.motechproject.whp.reports.contract.patient;

import lombok.Data;
import org.joda.time.LocalDate;

@Data
public class Treatment {
    private String providerId;
    private String providerDistrict;
    private String tbId;
    private LocalDate startDate;
    private LocalDate endDate;

    private String treatmentOutcome;
    private String patientType;
    private String tbRegistrationNumber;
    private String preTreatmentSmearTestResult;
    private int preTreatmentWeight;

    private boolean currentTreatment;

    private boolean isPaused;
    private LocalDate pausedDate;
    private String reasonsForPause;
    private int totalPausedDuration;//days
}
