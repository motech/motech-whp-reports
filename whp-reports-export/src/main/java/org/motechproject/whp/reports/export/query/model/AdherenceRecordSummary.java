package org.motechproject.whp.reports.export.query.model;

import lombok.Data;

import java.util.Date;

@Data
public class AdherenceRecordSummary {
    private String patientId;
    private String tbId;
    private Date adherenceDate;
    private String adherenceValue;
    private String district;
}
