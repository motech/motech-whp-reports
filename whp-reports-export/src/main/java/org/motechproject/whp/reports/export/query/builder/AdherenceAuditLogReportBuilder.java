package org.motechproject.whp.reports.export.query.builder;

import org.motechproject.whp.reports.export.query.model.AdherenceAuditLogSummary;
import org.motechproject.whp.reports.export.query.service.ExcelExporter;
import org.motechproject.whp.reports.export.query.service.ReportQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AdherenceAuditLogReportBuilder extends ReportBuilder{
    public static final String ADHERENCE_AUDIT_LOG_SUMMARY_TEMPLATE_FILE_NAME = "/xls/templates/adherenceAuditLogReport.xls";
    public static final String TEMPLATE_RESULT_KEY = "auditLogs";

    private ReportQueryService reportQueryService;

    @Autowired
    public AdherenceAuditLogReportBuilder(ReportQueryService reportQueryService, ExcelExporter excelExporter) {
        super(excelExporter);
        this.reportQueryService = reportQueryService;
    }

    public void buildAdherenceAuditLogReport(OutputStream outputStream) {
        build(outputStream, getReportData(), ADHERENCE_AUDIT_LOG_SUMMARY_TEMPLATE_FILE_NAME);
    }

    private Map<String, Object> getReportData() {
        List<AdherenceAuditLogSummary> adherenceAuditLogSummaries = reportQueryService.getAdherenceAuditLogSummaries();
        return setReportParameters(adherenceAuditLogSummaries);
    }
    private Map<String, Object> setReportParameters(List<AdherenceAuditLogSummary> adherenceAuditLogSummaries) {
        Map<String, Object> params = new HashMap<>();
        params.put(TEMPLATE_RESULT_KEY, adherenceAuditLogSummaries);
        return params;
    }
}
