package org.motechproject.whp.reports.export.query.builder;

import org.motechproject.whp.reports.export.query.model.AdherenceRecordSummary;
import org.motechproject.whp.reports.export.query.service.ExcelExporter;
import org.motechproject.whp.reports.export.query.service.ReportQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AdherenceRecordsReportBuilder extends ReportBuilder {

    public static final String ADHERENCE_RECORD_SUMMARY_TEMPLATE_FILE_NAME = "/xls/templates/adherenceReport.xls";
    public static final String TEMPLATE_RESULT_KEY = "adherenceRecords";

    private ReportQueryService reportQueryService;

    @Autowired
    public AdherenceRecordsReportBuilder(ReportQueryService reportQueryService, ExcelExporter excelExporter) {
        super(excelExporter);
        this.reportQueryService = reportQueryService;
    }

    public void buildAdherenceRecordReport(OutputStream outputStream) {
        build(outputStream, getReportData(), ADHERENCE_RECORD_SUMMARY_TEMPLATE_FILE_NAME);
    }

    private Map<String, Object> getReportData() {
        List<AdherenceRecordSummary> adherenceRecordSummaries = reportQueryService.getAdherenceRecordSummaries();
        return setReportParameters(adherenceRecordSummaries);
    }
    private Map<String, Object> setReportParameters(List<AdherenceRecordSummary> adherenceRecordSummaries) {
        Map<String, Object> params = new HashMap<>();
        params.put(TEMPLATE_RESULT_KEY, adherenceRecordSummaries);
        return params;
    }


}
