package org.motechproject.whp.reports.contract.patient;

import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class Therapy {
    private String therapyId;
    private String currentTherapy;
    private Integer patientAge;
    private Date creationDate;
    private Date startDate;
    private Date closeDate;
    private String status;
    private String treatmentCategory;
    private String diseaseClass;

    private String currentPhase;

    private Date ipStartDate;
    private Date ipEndDate;
    private int ipPillsTaken;
    private int ipPillsRemaining;
    private int ipTotalDoses;

    private Date cpStartDate;
    private Date cpEndDate;
    private int cpPillsTaken;
    private int cpPillsRemaining;
    private int cpTotalDoses;

    private Date eipStartDate;
    private Date eipEndDate;
    private int eipPillsTaken;
    private int eipPillsRemaining;
    private int eipTotalDoses;

    private List<Treatment> treatments;
}
