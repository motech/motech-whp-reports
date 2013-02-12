package org.motechproject.whp.reports.export.query.controller;

import org.motechproject.whp.reports.export.query.builder.PatientSummaryReportBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequestMapping(value = "/patientreports")
@Controller
public class ReportsController {

    public static final String CONTENT_DISPOSITION = "Content-Disposition";
    public static final String APPLICATION_VND_MS_EXCEL = "application/vnd.ms-excel";
    private PatientSummaryReportBuilder patientSummaryReportBuilder;

    @Autowired
    public ReportsController(PatientSummaryReportBuilder patientSummaryReportBuilder) {
        this.patientSummaryReportBuilder = patientSummaryReportBuilder;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{reportName}.xls")
    public void createExcelReportForPatientSummary(@PathVariable String reportName, HttpServletResponse response) throws IOException {
        initializeExcelResponse(response, reportName + ".xls");
        patientSummaryReportBuilder.build(response.getOutputStream());
    }

    private void initializeExcelResponse(HttpServletResponse response, String fileName) {
        response.setHeader(CONTENT_DISPOSITION, "inline; filename=" + fileName);
        response.setContentType(APPLICATION_VND_MS_EXCEL);
    }
}
