package org.motechproject.whp.reports.export.query.builder;

import org.motechproject.whp.reports.export.query.model.ProviderReminderCallLogSummary;
import org.motechproject.whp.reports.export.query.service.ReportQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProviderReminderCallLogReportBuilder implements ReportBuilder{

    private final ReportQueryService reportQueryService;
    private ExcelReportBuilder excelReportBuilder;
    public static final String PROVIDER_REMINDER_CALL_LOG_TEMPLATE_NAME = "/xls/templates/providerReminderCallLogReport.xls";

    @Autowired
    public ProviderReminderCallLogReportBuilder(ReportQueryService reportQueryService, ExcelReportBuilder excelReportBuilder) {
        this.reportQueryService = reportQueryService;
        this.excelReportBuilder = excelReportBuilder;
    }

    public void build(OutputStream outputStream) {
        excelReportBuilder.build(outputStream,getReportData(), PROVIDER_REMINDER_CALL_LOG_TEMPLATE_NAME);
    }

    private Map<String, Object> getReportData() {
        List<ProviderReminderCallLogSummary> providerReminderCallLogSummaries = reportQueryService.getProviderReminderCallLogSummaries();
        Map<String, Object> params = new HashMap<>();
        params.put("callLogs", providerReminderCallLogSummaries);
        return params;
    }

    @Override
    public String getReportName() {
        return "providerReminderCallLogReport";
    }
}
