package org.motechproject.whp.reports.export.query.builder;

import org.motechproject.whp.reports.export.query.service.ReportQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

@Component
public class ContainerReportBuilder implements ReportBuilder {
    public static final String CONTAINER_REPORT_TEMPLATE_FILE_NAME = "/xls/templates/containerReport.xls";

    private ReportQueryService reportQueryService;
    private ExcelReportBuilder excelReportBuilder;

    @Autowired
    public ContainerReportBuilder(ReportQueryService reportQueryService, ExcelReportBuilder excelReportBuilder) {
        this.reportQueryService = reportQueryService;
        this.excelReportBuilder = excelReportBuilder;
    }

    public void build(OutputStream outputStream) {
        excelReportBuilder.build(outputStream, getReportData(), CONTAINER_REPORT_TEMPLATE_FILE_NAME);
    }

    private Map<String, Object> getReportData() {
       // List<ContainerSummary> containerSummaries = reportQueryService.();
        Map<String, Object> params = new HashMap<>();
//       params.put("auditLogs", );
        return params;
    }

    @Override
    public String getReportName() {
        return "containerTrackingReport";
    }
}
