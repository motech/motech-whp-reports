package org.motechproject.whp.reports.export.query.builder;

import org.apache.poi.ss.usermodel.Workbook;
import org.motechproject.whp.reports.export.query.service.ExcelExporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public class ReportBuilder {
    private final ExcelExporter excelExporter;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public ReportBuilder(ExcelExporter excelExporter) {
        this.excelExporter = excelExporter;
    }

    void build(OutputStream outputStream, Map params, String templateFileName) {
        try {
            Workbook workbook = excelExporter.export(templateFileName, params);
            if (workbook != null) {
                workbook.write(outputStream);
            }
            outputStream.flush();
        } catch (IOException e) {
            logger.error("Error while writing excel report to response: " + e.getMessage());
        }
    }
}