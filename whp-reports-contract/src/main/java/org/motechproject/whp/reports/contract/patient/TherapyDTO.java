package org.motechproject.whp.reports.contract.patient;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Data
public class TherapyDTO implements Serializable {
    private static final long serialVersionUID = 1L;
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
    private Integer ipPillsTaken;
    private Integer ipPillsRemaining;
    private Integer ipTotalDoses;

    private Date cpStartDate;
    private Date cpEndDate;
    private Integer cpPillsTaken;
    private Integer cpPillsRemaining;
    private Integer cpTotalDoses;

    private Date eipStartDate;
    private Date eipEndDate;
    private Integer eipPillsTaken;
    private Integer eipPillsRemaining;
    private Integer eipTotalDoses;

    private List<TreatmentDTO> treatments;
}
