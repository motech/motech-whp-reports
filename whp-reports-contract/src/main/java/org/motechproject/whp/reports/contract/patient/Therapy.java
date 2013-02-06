package org.motechproject.whp.reports.contract.patient;

import lombok.Data;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import java.util.List;

@Data
public class Therapy {
    private String therapyId;
    private boolean currentTherapy;
    private Integer patientAge;
    private DateTime creationDate;
    private LocalDate startDate;
    private LocalDate closeDate;
    private String status;
    private String treatmentCategory;
    private String diseaseClass;

    private String currentPhase;

    private LocalDate ipStartDate;
    private LocalDate ipEndDate;
    private int ipPillsTaken;
    private int ipPillsRemaining;
    private int ipTotalDoses;

    private LocalDate cpStartDate;
    private LocalDate cpEndDate;
    private int cpPillsTaken;
    private int cpPillsRemaining;
    private int cpTotalDoses;

    private LocalDate eipStartDate;
    private LocalDate eipEndDate;
    private int eipPillsTaken;
    private int eipPillsRemaining;
    private int eipTotalDoses;

    private List<Treatment> treatments;
}
