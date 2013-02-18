package org.motechproject.whp.reports.export.query.model;

import lombok.Data;
import org.motechproject.whp.reports.export.query.dao.PatientReportType;

@Data
public class PatientReportRequest {
    private String district;
    private String from;
    private String to;
    private PatientReportType reportType;
}
