package org.motechproject.whp.reports.export.query.controller;

import org.motechproject.whp.reports.export.query.builder.AdherenceRecordsReportBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping(value= "adherenceRecords" )
public class AdherenceRecordReportsController {

    public static final String CONTENT_DISPOSITION = "Content-Disposition";
    public static final String APPLICATION_VND_MS_EXCEL = "application/vnd.ms-excel";
    private AdherenceRecordsReportBuilder adherenceRecordsReportBuilder;

    @Autowired
    public AdherenceRecordReportsController(AdherenceRecordsReportBuilder adherenceRecordsReportBuilder) {
        this.adherenceRecordsReportBuilder = adherenceRecordsReportBuilder;
    }

    @RequestMapping(method = RequestMethod.GET, value= "/adherenceReport.xls")
    public void createExcelResponse(HttpServletResponse response) throws IOException {
        initializeExcelResponse(response, "adherenceReport.xls");
        adherenceRecordsReportBuilder.buildAdherenceRecordReport(response.getOutputStream());
    }

    private void initializeExcelResponse(HttpServletResponse response, String fileName) {
        response.setHeader(CONTENT_DISPOSITION, "inline; filename=" + fileName);
        response.setContentType(APPLICATION_VND_MS_EXCEL);
    }
}
