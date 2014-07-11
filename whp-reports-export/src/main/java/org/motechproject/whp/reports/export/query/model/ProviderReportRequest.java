package org.motechproject.whp.reports.export.query.model;

import lombok.Data;

import org.motechproject.whp.reports.export.query.dao.ProviderReportType;

@Data
public class ProviderReportRequest {
    private String district;
    private String from;
    private String to;
    private ProviderReportType reportType;
}
