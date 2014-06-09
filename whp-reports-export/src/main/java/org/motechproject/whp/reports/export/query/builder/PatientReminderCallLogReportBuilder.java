package org.motechproject.whp.reports.export.query.builder;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.motechproject.whp.reports.export.query.model.PatientReminderCallLogSummary;
import org.motechproject.whp.reports.export.query.service.ReportQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientReminderCallLogReportBuilder implements ReportBuilder {
	private final ReportQueryService reportQueryService;
    private WhpExcelReportBuilder whpExcelReportBuilder;
    public static final String PATIENT_REMINDER_CALL_LOG_TEMPLATE_NAME = "/xls/templates/patientReminderCallLogReport.xls";

    @Autowired
    public PatientReminderCallLogReportBuilder(ReportQueryService reportQueryService, WhpExcelReportBuilder whpExcelReportBuilder) {
        this.reportQueryService = reportQueryService;
        this.whpExcelReportBuilder = whpExcelReportBuilder;
    }

    public void build(OutputStream outputStream) {
        whpExcelReportBuilder.build(outputStream,getReportData(), PATIENT_REMINDER_CALL_LOG_TEMPLATE_NAME);
    }

    private Map<String, Object> getReportData() {
    List<PatientReminderCallLogSummary> patientReminderCallLogSummaries = reportQueryService.getPatientReminderCallLogSummaries();
        Map<String, Object> params = new HashMap<>(); 
        params.put("callLogs", patientReminderCallLogSummaries);
        return params;
    }

    @Override
    public String getReportName() {
        return "patientReminderCallLogReport";
    }
}
