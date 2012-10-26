package org.motechproject.whp.reports.contract;

import lombok.Data;

import java.util.Date;

@Data
public class SputumTrackingRequest {
    private String containerId;
    private Date dateIssuedOn;
    private String providerId;
    private String submittedBy;
    private String submitterId;
    private String locationId;
    private String instance;
    private String channelId;
    private String patientId;
    private String containerStatus;
    private String reasonForClosure;
    private String alternateDiagnosisCode;
}
