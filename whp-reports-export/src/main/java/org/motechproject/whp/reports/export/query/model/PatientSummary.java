package org.motechproject.whp.reports.export.query.model;

import lombok.Data;
import org.apache.commons.lang.StringUtils;

import java.util.Date;

@Data
public class PatientSummary {
    private String name;
    private String firstName;
    private String lastName;
    private Integer age;
    private String gender;
    private String patientId;
    private String tbId;
    private String providerId;
    private String village;
    private String providerDistrict;
    private String treatmentCategory;
    private Date tbRegistrationDate;
    private Date treatmentStartDate;
    private String diseaseClass;
    private String patientType;
    private Integer ipPillsTaken;
    private Integer ipTotalDoses;
    private Integer eipPillsTaken;
    private Integer eipTotalDoses;
    private Integer cpPillsTaken;
    private Integer cpTotalDoses;
    private Integer cumulativeMissedDoses;
    private String treatmentOutcome;
    private Date treatmentClosingDate;
    private String preTreatmentSputumResult;
    private Double preTreatmentWeight;

    public String getName() {
        if(StringUtils.isNotBlank(lastName))
            return String.format("%s %s", firstName, lastName);
        return firstName;
    }


    public String getIpTreatmentProgress() {
        int totalDoseCount = 0;
        int totalDoseTakenCount = 0;

        if (eipTotalDoses == null || eipPillsTaken == null){
            totalDoseCount = ipTotalDoses;
            totalDoseTakenCount = ipPillsTaken;
        }
        else {
            totalDoseCount = ipTotalDoses + eipTotalDoses;
            totalDoseTakenCount = ipPillsTaken + eipPillsTaken;
        }

        return doseCompletionMessage(totalDoseCount, totalDoseTakenCount);
    }

    public String getCpTreatmentProgress() {
        return doseCompletionMessage(cpTotalDoses, cpPillsTaken);
    }

    private String doseCompletionMessage(Integer totalDoseCount, Integer totalDoseTakenCount) {
        if (totalDoseCount == null || totalDoseCount == 0) {
            return String.format("%d/%d (%.2f%%)", totalDoseTakenCount, totalDoseCount, 0.0f);
        } else {
            return String.format("%d/%d (%.2f%%)", totalDoseTakenCount, totalDoseCount, (totalDoseTakenCount / (float) totalDoseCount) * 100);
        }
    }

}