package org.motechproject.whp.reports.webservice.model;

import lombok.Setter;
import org.motechproject.export.annotation.ExportValue;

import java.util.Date;

import static org.motechproject.whp.reports.date.WHPDate.DATE_FORMAT;

@Setter
public class ContainerSummary {

    private String containerId;
    private Date issuedOn;
    private String providerId;
    private String providerDistrict;
    private String submitterId;
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

    @ExportValue(index = 1)
    public String getContainerId() {
        return containerId;
    }

    @ExportValue(index = 2, format = DATE_FORMAT)
    public Date getIssuedOn() {
        return issuedOn;
    }

    @ExportValue(index = 3)
    public String getProviderId() {
        return providerId;
    }

    @ExportValue(index = 4)
    public String getProviderDistrict() {
        return providerDistrict;
    }


    @ExportValue(index = 5)
    public String getRegisteredBy() {
        return submitterId;
    }

    @ExportValue(index = 6)
    public String getRegistrationInstance() {
        return registrationInstance;
    }

    @ExportValue(index = 7, column = "Channel")
    public String getChannelId() {
        return channelId;
    }

    @ExportValue(index = 8)
    public String getPatientId() {
        return patientId;
    }


    @ExportValue(index = 9)
    public String getTbId() {
        return tbId;
    }

    @ExportValue(index = 10)
    public String getMappingInstance() {
        return mappingInstance;
    }

    @ExportValue(index = 11, format = DATE_FORMAT)
    public Date getSmearTestDate1() {
        return smearTestDate1;
    }

    @ExportValue(index = 12)
    public String getSmearTestResult1() {
        return smearTestResult1;
    }

    @ExportValue(index = 13, format = DATE_FORMAT)
    public Date getSmearTestDate2() {
        return smearTestDate2;
    }

    @ExportValue(index = 14)
    public String getSmearTestResult2() {
        return smearTestResult2;
    }

    @ExportValue(index = 15)
    public String getLabName() {
        return labName;
    }

    @ExportValue(index = 16)
    public String getLabNumber() {
        return labNumber;
    }

    @ExportValue(index = 17, format = DATE_FORMAT)
    public Date getLabResultsCapturedOn() {
        return labResultsCapturedOn;
    }

    @ExportValue(index = 18)
    public String getStatus() {
        return status;
    }

    @ExportValue(index = 19)
    public String getReasonForClosure() {
        return reasonForClosure;
    }

    @ExportValue(index = 20,format = DATE_FORMAT)
    public Date getClosureDate() {
        return closureDate;
    }

    @ExportValue(index = 21, format = DATE_FORMAT)
    public Date getConsultationDate() {
        return consultationDate;
    }

    @ExportValue(index = 22)
    public String getDiagnosis() {
        return diagnosis;
    }

    @ExportValue(index = 23)
    public String getAlternateDiagnosisCode() {
        return alternateDiagnosisCode;
    }

    @ExportValue(index = 24)
    public String getAlternateDiagnosisName() {
        return alternateDiagnosisName;
    }
}
