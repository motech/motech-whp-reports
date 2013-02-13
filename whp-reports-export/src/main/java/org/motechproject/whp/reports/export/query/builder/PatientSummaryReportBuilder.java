package org.motechproject.whp.reports.export.query.builder;

import org.motechproject.whp.reports.export.query.model.PatientReportRequest;
import org.motechproject.whp.reports.export.query.model.PatientSummary;
import org.motechproject.whp.reports.export.query.service.ExcelExporter;
import org.motechproject.whp.reports.export.query.service.ReportQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PatientSummaryReportBuilder extends ReportBuilder {

    private final ReportQueryService reportQueryService;
    public static final String TEMPLATE_FILE_NAME = "/xls/templates/patientSummaryReport.xls";
    private static final String TEMPLATE_RESULT_KEY = "patients";

    @Autowired
    public PatientSummaryReportBuilder(ReportQueryService reportQueryService, ExcelExporter excelExporter) {
        super(excelExporter);
        this.reportQueryService = reportQueryService;
    }

    public void build(PatientReportRequest patientReportRequest, OutputStream outputStream) {
        List<PatientSummary> patientSummaries = reportQueryService.getPatientSummaries(patientReportRequest);
        Map<String, Object> params = new HashMap<>();
        params.put(TEMPLATE_RESULT_KEY, patientSummaries);

        build(outputStream, params, TEMPLATE_FILE_NAME);
    }
}
