package org.motechproject.whp.reports.contract.adherence;

import lombok.Data;
import org.joda.time.DateTime;

@Data
public class AdherenceAuditLogDTO {
    private String patientId;
    private String providerId;
    private String tbId;
    private DateTime creationTime;
    private DateTime doseDate;
    private String userId;
    private Integer numberOfDosesTaken;
    private String pillStatus;
    private String sourceOfChange;
}
