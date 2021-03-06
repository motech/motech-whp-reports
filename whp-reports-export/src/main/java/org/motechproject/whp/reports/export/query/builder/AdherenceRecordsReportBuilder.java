package org.motechproject.whp.reports.export.query.builder;

import org.motechproject.whp.reports.export.query.model.AdherenceRecordSummary;
import org.motechproject.whp.reports.export.query.service.ReportQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AdherenceRecordsReportBuilder implements ReportBuilder {

    public static final String ADHERENCE_RECORD_SUMMARY_TEMPLATE_FILE_NAME = "/xls/templates/adherenceReport.xls";

    private ReportQueryService reportQueryService;
    private WhpExcelReportBuilder whpExcelReportBuilder;

    @Autowired
    public AdherenceRecordsReportBuilder(ReportQueryService reportQueryService, WhpExcelReportBuilder whpExcelReportBuilder) {
        this.reportQueryService = reportQueryService;
        this.whpExcelReportBuilder = whpExcelReportBuilder;
    }

    public void build(OutputStream outputStream) {
        whpExcelReportBuilder.build(outputStream, getReportData(), ADHERENCE_RECORD_SUMMARY_TEMPLATE_FILE_NAME);
    }

    private Map<String, Object> getReportData() {
        List<AdherenceRecordSummary> adherenceRecordSummaries = reportQueryService.getAdherenceRecordSummaries();
        Map<String, Object> params = new HashMap<>();
        params.put("adherenceRecords", adherenceRecordSummaries);
        return params;
    }

    @Override
    public String getReportName() {
        return "adherenceReport";
    }
}
