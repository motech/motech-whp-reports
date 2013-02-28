package org.motechproject.whp.reports.export.query.builder;

import java.io.OutputStream;

public interface ReportBuilder {
    String getReportName();
    void build(OutputStream outputStream);
}
