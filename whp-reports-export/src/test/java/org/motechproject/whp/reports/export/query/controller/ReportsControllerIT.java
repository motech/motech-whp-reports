package org.motechproject.whp.reports.export.query.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.motechproject.whp.reports.export.query.builder.AdherenceAuditLogReportBuilder;
import org.motechproject.whp.reports.export.query.builder.AdherenceRecordsReportBuilder;
import org.motechproject.whp.reports.export.query.builder.ProviderReminderCallLogReportBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationReportingExportContext.xml")
public class ReportsControllerIT {

    @Autowired
    ReportsController reportsController;

    @Autowired
    AdherenceAuditLogReportBuilder adherenceAuditLogReportBuilder;
    @Autowired
    AdherenceRecordsReportBuilder adherenceRecordsReportBuilder;
    @Autowired
    ProviderReminderCallLogReportBuilder providerReminderCallLogReportBuilder;

    @Test
    public void shouldContainAllReportBuilders() {
        assertEquals(4, reportsController.reportBuilders.size());
        assertEquals(adherenceRecordsReportBuilder, reportsController.reportBuilders.get(adherenceRecordsReportBuilder.getReportName()));
        assertEquals(adherenceAuditLogReportBuilder, reportsController.reportBuilders.get(adherenceAuditLogReportBuilder.getReportName()));
        assertEquals(providerReminderCallLogReportBuilder, reportsController.reportBuilders.get(providerReminderCallLogReportBuilder.getReportName()));
    }


}
