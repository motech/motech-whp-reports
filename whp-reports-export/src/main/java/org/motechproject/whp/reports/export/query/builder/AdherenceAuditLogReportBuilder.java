package org.motechproject.whp.reports.export.query.builder;

import org.motechproject.whp.reports.export.query.model.AdherenceAuditLogSummary;
import org.motechproject.whp.reports.export.query.service.ReportQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AdherenceAuditLogReportBuilder implements ReportBuilder {
    public static final String ADHERENCE_AUDIT_LOG_SUMMARY_TEMPLATE_FILE_NAME = "/xls/templates/adherenceAuditLogReport.xls";

    private ReportQueryService reportQueryService;
    private WhpExcelReportBuilder whpExcelReportBuilder;

    @Autowired
    public AdherenceAuditLogReportBuilder(ReportQueryService reportQueryService, WhpExcelReportBuilder whpExcelReportBuilder) {
        this.reportQueryService = reportQueryService;
        this.whpExcelReportBuilder = whpExcelReportBuilder;
    }

    public void build(OutputStream outputStream) {
        whpExcelReportBuilder.build(outputStream, getReportData(), ADHERENCE_AUDIT_LOG_SUMMARY_TEMPLATE_FILE_NAME);
    }

    private Map<String, Object> getReportData() {
        List<AdherenceAuditLogSummary> adherenceAuditLogSummaries = reportQueryService.getAdherenceAuditLogSummaries();
        Map<String, Object> params = new HashMap<>();
        params.put("auditLogs", adherenceAuditLogSummaries);
        return params;
    }

    @Override
    public String getReportName() {
        return "adherenceAuditLogReport";
    }
}
