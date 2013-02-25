package org.motechproject.whp.reports.export.query.model;

import lombok.Data;

import java.util.Date;

@Data
public class AdherenceAuditLogSummary {
    private String patientId;
    private String providerId;
    private String tbId;
    private Date creationTime;
    private Date doseDate;
    private String userId;
    private Integer numberOfDosesTaken;
    private String pillStatus;
    private String sourceOfChange;
    private String district;
    private String isGivenByProvider;

    public Date getCreationTime(){
        return new Date(creationTime.getTime());
    }
}
