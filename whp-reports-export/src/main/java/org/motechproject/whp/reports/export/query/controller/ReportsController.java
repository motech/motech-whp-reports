package org.motechproject.whp.reports.export.query.controller;

import org.motechproject.whp.reports.export.query.builder.ReportBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Controller
public class ReportsController {

    public static final String CONTENT_DISPOSITION = "Content-Disposition";
    public static final String APPLICATION_VND_MS_EXCEL = "application/vnd.ms-excel";
    Map<String, ReportBuilder> reportBuilders = new HashMap<>();

    @Autowired
    public ReportsController(Set<ReportBuilder> reportBuilderSet) {
        for(ReportBuilder reportBuilder : reportBuilderSet){
            reportBuilders.put(reportBuilder.getReportName(), reportBuilder);
        }
    }

    @RequestMapping("/reports/{reportName}")
    public void export(@PathVariable String reportName, HttpServletResponse response) throws IOException {
        initializeExcelResponse(response, reportName);
        reportBuilders.get(reportName).build(response.getOutputStream());
    }

    private void initializeExcelResponse(HttpServletResponse response, String reportName) {
        response.setHeader(CONTENT_DISPOSITION, "inline; filename=" + reportName + ".xls");
        response.setContentType(APPLICATION_VND_MS_EXCEL);
    }
}
