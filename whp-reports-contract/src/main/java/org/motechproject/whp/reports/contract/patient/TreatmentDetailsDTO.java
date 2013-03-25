package org.motechproject.whp.reports.contract.patient;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class TreatmentDetailsDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String districtWithCode;
    private String tbUnitWithCode;
    private String epSite;
    private String otherInvestigations;
    private String previousTreatmentHistory;
    private String hivStatus;
    private Date hivTestDate;
    private Integer membersBelowSixYears;
    private String phcReferred;
    private String providerName;
    private String dotCentre;
    private String providerType;
    private String cmfDoctor;
    private String contactPersonName;
    private String contactPersonPhoneNumber;
    private String xpertTestResult;
    private String xpertDeviceNumber;
    private Date xpertTestDate;
    private String rifResistanceResult;
}
