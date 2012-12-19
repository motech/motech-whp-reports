package org.motechproject.whp.reports.contract;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ContainerRegistrationReportingRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    private String containerId;
    private Date issuedOn;
    private String providerId;
    private String submitterRole;
    private String submitterId;
    private String providerDistrict;
    private String instance;
    private String channelId;
    private String callId;
    private String patientId;
    private String status;
    private String diagnosis;
}
