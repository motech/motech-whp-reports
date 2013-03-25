package org.motechproject.whp.reports.contract.patient;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class TreatmentDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String providerId;
    private String providerDistrict;
    private String tbId;
    private Date startDate;
    private Date endDate;

    private String treatmentOutcome;
    private String patientType;
    private String tbRegistrationNumber;
    private String preTreatmentSmearTestResult;
    private Double preTreatmentWeight;

    private String currentTreatment;

    private String isPaused;
    private Date pausedDate;
    private String reasonsForPause;
    private int totalPausedDuration;//days
    private String closeTreatmentRemarks;
    private TreatmentDetailsDTO treatmentDetails = new TreatmentDetailsDTO();

}
