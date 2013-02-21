package org.motechproject.whp.reports.export.query.model;

import lombok.Data;

import java.sql.Timestamp;
import java.sql.Date;

@Data
public class AdherenceAuditLogSummary {
    private String patientId;
    private String providerId;
    private String tbId;
    private Timestamp creationTime;
    private Date doseDate;
    private String userId;
    private Integer numberOfDosesTaken;
    private String pillStatus;
    private String sourceOfChange;
    private String district;

    public String getDistrict(){
        if (numberOfDosesTaken == null){
            return "";
        }
        else return district;
    }

}
