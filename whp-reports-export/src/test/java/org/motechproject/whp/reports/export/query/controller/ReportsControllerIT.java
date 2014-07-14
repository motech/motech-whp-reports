package org.motechproject.whp.reports.export.query.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.motechproject.whp.reports.export.query.builder.AdherenceAuditLogReportBuilder;
import org.motechproject.whp.reports.export.query.builder.AdherenceRecordsReportBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationReportingExportContext.xml")
public class ReportsControllerIT {

    @Autowired
    WhpReportsController whpReportsController;

    @Autowired
    AdherenceAuditLogReportBuilder adherenceAuditLogReportBuilder;
    @Autowired
    AdherenceRecordsReportBuilder adherenceRecordsReportBuilder;

    @Test
    public void shouldContainAllReportBuilders() {
        assertEquals(2, whpReportsController.reportBuilders.size());
        assertEquals(adherenceRecordsReportBuilder, whpReportsController.reportBuilders.get(adherenceRecordsReportBuilder.getReportName()));
        assertEquals(adherenceAuditLogReportBuilder, whpReportsController.reportBuilders.get(adherenceAuditLogReportBuilder.getReportName()));
    }


}
