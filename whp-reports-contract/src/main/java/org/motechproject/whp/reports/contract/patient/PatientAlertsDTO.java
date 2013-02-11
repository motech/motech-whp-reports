package org.motechproject.whp.reports.contract.patient;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class PatientAlertsDTO implements Serializable {
    private int cumulativeMissedDoses;
    private int cumulativeMissedDosesAlertSeverity;
    private Date cumulativeMissedDosesAlertDate;

    private int adherenceMissingWeeks;
    private int adherenceMissingWeeksAlertSeverity;
    private Date adherenceMissingWeeksAlertDate;

    private int treatmentNotStarted;
    private int treatmentNotStartedAlertSeverity;
    private Date treatmentNotStartedAlertDate;
}
