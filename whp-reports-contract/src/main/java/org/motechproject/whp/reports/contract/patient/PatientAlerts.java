package org.motechproject.whp.reports.contract.patient;

import lombok.Data;
import org.joda.time.LocalDate;

@Data
public class PatientAlerts {
    private int cumulativeMissedDoses;
    private int cumulativeMissedDosesAlertSeverity;
    private LocalDate cumulativeMissedDosesAlertDate;

    private int adherenceMissingWeeks;
    private int adherenceMissingWeeksAlertSeverity;
    private LocalDate adherenceMissingWeeksAlertDate;

    private int treatmentNotStarted;
    private int treatmentNotStartedAlertSeverity;
    private LocalDate treatmentNotStartedAlertDate;
}
