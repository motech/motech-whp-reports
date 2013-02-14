package org.motechproject.whp.reports.export.query.model;

import lombok.Data;

@Data
public class PatientReportRequest {
    private String district;
    private String from;
    private String to;
}
