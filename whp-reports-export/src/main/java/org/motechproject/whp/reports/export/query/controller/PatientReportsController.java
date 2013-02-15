package org.motechproject.whp.reports.export.query.controller;

import org.motechproject.whp.reports.export.query.builder.PatientSummaryReportBuilder;
import org.motechproject.whp.reports.export.query.model.PatientReportRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequestMapping(value = "/patientreports")
@Controller
public class PatientReportsController {

    public static final String CONTENT_DISPOSITION = "Content-Disposition";
    public static final String APPLICATION_VND_MS_EXCEL = "application/vnd.ms-excel";
    private PatientSummaryReportBuilder patientSummaryReportBuilder;

    @Autowired
    public PatientReportsController(PatientSummaryReportBuilder patientSummaryReportBuilder) {
        this.patientSummaryReportBuilder = patientSummaryReportBuilder;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/patientSummary.xls")
    public void createExcelReportForPatientSummary(PatientReportRequest request, HttpServletResponse response) throws IOException {
        initializeExcelResponse(response, "patientSummary.xls");
        patientSummaryReportBuilder.buildSummaryReport(request, response.getOutputStream());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/patientRegistrations.xls")
    public void createExcelReportForPatientRegistrations(PatientReportRequest request, HttpServletResponse response) throws IOException {
        initializeExcelResponse(response, "patientRegistrations.xls");
        patientSummaryReportBuilder.buildRegistrationsReport(request, response.getOutputStream());
    }

    private void initializeExcelResponse(HttpServletResponse response, String fileName) {
        response.setHeader(CONTENT_DISPOSITION, "inline; filename=" + fileName);
        response.setContentType(APPLICATION_VND_MS_EXCEL);
    }
}
