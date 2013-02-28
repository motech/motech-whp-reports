package org.motechproject.whp.reports.export.query.model;

import lombok.Data;

import java.sql.Date;


@Data
public class ContainerSummary {
    private String containerId;
    private Date dateIssuedOn;
    private String submitterId;
    private String providerId;
    private String providerDistrict;
    private String registrationInstance;
    private String channelId;
    private Date smearTestDate1;
    private String smearTestResult1;
    private Date smearTestDate2;
    private String smearTestResult2;
    private String labName;
    private String labNumber;
    private Date labResultsCapturedOn;
    private String status;
    private String reasonForClosure;
    private String diagnosis;
    private String alternateDiagnosisCode;
    private Date closureDate;
    private String patientId;
    private String tbId;
    private Date consultationDate;
    private String mappingInstance;
    private String alternateDiagnosisName;
}
