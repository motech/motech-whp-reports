package org.motechproject.whp.reports.export.query.controller;

import org.motechproject.whp.reports.export.query.builder.AdherenceAuditLogReportBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequestMapping(value = "/auditreports")
@Controller
public class AdherenceAuditLogReportsController {

    public static final String CONTENT_DISPOSITION = "Content-Disposition";
    public static final String APPLICATION_VND_MS_EXCEL = "application/vnd.ms-excel";
    private AdherenceAuditLogReportBuilder adherenceAuditLogReportBuilder;

    @Autowired
    public AdherenceAuditLogReportsController(AdherenceAuditLogReportBuilder adherenceAuditLogReportBuilder) {
        this.adherenceAuditLogReportBuilder = adherenceAuditLogReportBuilder;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/adherenceAuditLogReport.xls")
    public void createExcelReportForAdherenceAuditLog(HttpServletResponse response) throws IOException {
        initializeExcelResponse(response, "adherenceAuditLogReport.xls");
        adherenceAuditLogReportBuilder.buildAdherenceAuditLogReport(response.getOutputStream());
    }

    private void initializeExcelResponse(HttpServletResponse response, String fileName) {
        response.setHeader(CONTENT_DISPOSITION, "inline; filename=" + fileName);
        response.setContentType(APPLICATION_VND_MS_EXCEL);
    }

}
