package org.motechproject.whp.reports.export.query.builder;

import org.motechproject.util.DateUtil;
import org.motechproject.whp.reports.export.query.model.DateRange;
import org.motechproject.whp.reports.export.query.model.PatientReportRequest;
import org.motechproject.whp.reports.export.query.model.PatientSummary;
import org.motechproject.whp.reports.export.query.service.ReportQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PatientReportBuilder {

    public static final String DATE_FORMAT = "dd/MM/yyyy";
    public static final String PATIENT_SUMMARY_TEMPLATE_FILE_NAME = "/xls/templates/patientSummaryReport.xls";
    public static final String PATIENT_REGISTRATIONS_TEMPLATE_FILE_NAME = "/xls/templates/patientRegistrationsReport.xls";
    public static final String PATIENT_CLOSED_TREATMENT_TEMPLATE_FILE_NAME = "/xls/templates/patientClosedTreatmentsReport.xls";
    public static final String TEMPLATE_RESULT_KEY = "patients";

    public static final String FROM_DATE = "fromDate";
    public static final String TO_DATE = "toDate";
    public static final String PROVIDER_DISTRICT = "providerDistrict";
    public static final String TOTAL_ROWS = "totalRows";

    private final ReportQueryService reportQueryService;
    private ExcelReportBuilder excelReportBuilder;

    @Autowired
    public PatientReportBuilder(ReportQueryService reportQueryService, ExcelReportBuilder excelReportBuilder) {
        this.reportQueryService = reportQueryService;
        this.excelReportBuilder = excelReportBuilder;
    }

    public void buildSummaryReport(PatientReportRequest patientReportRequest, OutputStream outputStream) {
        excelReportBuilder.build(outputStream, getReportData(patientReportRequest), PATIENT_SUMMARY_TEMPLATE_FILE_NAME);
    }

    public void buildRegistrationsReport(PatientReportRequest patientReportRequest, OutputStream outputStream) {
        excelReportBuilder.build(outputStream, getReportData(patientReportRequest), PATIENT_REGISTRATIONS_TEMPLATE_FILE_NAME);
    }

    public void buildClosedTreatmentsReport(PatientReportRequest patientReportRequest, OutputStream outputStream) {
        excelReportBuilder.build(outputStream, getReportData(patientReportRequest), PATIENT_CLOSED_TREATMENT_TEMPLATE_FILE_NAME);
    }

    private Map<String, Object> getReportData(PatientReportRequest patientReportRequest) {
        List<PatientSummary> patientSummaries = reportQueryService.getPatientSummaries(patientReportRequest);
        return setReportParameters(patientReportRequest, patientSummaries);
    }

    private Map<String, Object> setReportParameters(PatientReportRequest patientReportRequest, List<PatientSummary> patientSummaries) {
        Map<String, Object> params = new HashMap<>();

        String generatedOn = new SimpleDateFormat(DATE_FORMAT).format(DateUtil.now().toDate());
        DateRange dateRange = new DateRange(patientReportRequest.getFrom(), patientReportRequest.getTo());

        params.put(TEMPLATE_RESULT_KEY, patientSummaries);
        params.put(FROM_DATE, dateRange.getStartDate());
        params.put(TO_DATE, dateRange.getEndDate());
        params.put(PROVIDER_DISTRICT, patientReportRequest.getDistrict());
        params.put(TOTAL_ROWS, patientSummaries.size());
        return params;
    }
}
