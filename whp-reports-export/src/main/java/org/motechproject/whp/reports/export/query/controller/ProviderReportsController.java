package org.motechproject.whp.reports.export.query.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.motechproject.whp.reports.export.query.builder.ProviderReportBuilder;
import org.motechproject.whp.reports.export.query.dao.ProviderReportType;
import org.motechproject.whp.reports.export.query.model.ProviderReportRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(value = "/providerreports")
@Controller
public class ProviderReportsController {

    public static final String CONTENT_DISPOSITION = "Content-Disposition";
    public static final String APPLICATION_VND_MS_EXCEL = "application/vnd.ms-excel";
    private ProviderReportBuilder providerReportBuilder;

    @Autowired
    public ProviderReportsController(ProviderReportBuilder providerReportBuilder) {
        this.providerReportBuilder = providerReportBuilder;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/providerReminderCallLogReport.xls")
    public void createExcelReportForProviderReminderCallLogReport(ProviderReportRequest request, HttpServletResponse response) throws IOException {
        initializeExcelResponse(response, "providerReminderCallLogReport.xls");
        request.setReportType(ProviderReportType.REMINDER_CALL_LOG);
        providerReportBuilder.buildProviderReminderCallLogReport(request, response.getOutputStream());
    }
    
    private void initializeExcelResponse(HttpServletResponse response, String fileName) {
        response.setHeader(CONTENT_DISPOSITION, "inline; filename=" + fileName);
        response.setContentType(APPLICATION_VND_MS_EXCEL);
    }
}
