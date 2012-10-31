package org.motechproject.whp.reports.contract;

import lombok.Data;

import java.util.Date;

@Data
public class ContainerStatusReportingRequest {
    private String containerId;
    private String containerStatus;
    private String reasonForClosure;
    private String diagnosis;
    private String alternateDiagnosisCode;
    private Date consultationDate;
    private Date closureDate;
}
