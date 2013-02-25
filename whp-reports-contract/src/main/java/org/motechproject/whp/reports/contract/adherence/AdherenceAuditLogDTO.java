package org.motechproject.whp.reports.contract.adherence;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@Data
public class AdherenceAuditLogDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String patientId;
    private String providerId;
    private String tbId;
    private Timestamp creationTime;
    private Date doseDate;
    private String userId;
    private Integer numberOfDosesTaken;
    private String pillStatus;
    private String channel;
    private String isGivenByProvider;
}
