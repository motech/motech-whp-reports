package org.motechproject.whp.reports.export.query.builder;

import org.apache.poi.ss.usermodel.Workbook;
import org.motechproject.util.DateUtil;
import org.motechproject.whp.reports.date.WHPDateTime;
import org.motechproject.whp.reports.export.query.service.ExcelExporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

@Component
public class ExcelReportBuilder {
    public static final String GENERATED_ON = "generatedOn";
    private final ExcelExporter excelExporter;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public ExcelReportBuilder(ExcelExporter excelExporter) {
        this.excelExporter = excelExporter;
    }

    void build(OutputStream outputStream, Map params, String templateFileName) {
        params.put(GENERATED_ON, WHPDateTime.date(DateUtil.now()).value());
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