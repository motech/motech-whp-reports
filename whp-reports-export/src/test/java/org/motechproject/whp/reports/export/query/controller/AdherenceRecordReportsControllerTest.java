package org.motechproject.whp.reports.export.query.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.whp.reports.export.query.builder.AdherenceRecordsReportBuilder;

import java.io.OutputStream;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class AdherenceRecordReportsControllerTest {

    AdherenceRecordReportsController adherenceRecordReportsController;

    @Mock
    AdherenceRecordsReportBuilder adherenceRecordsReportBuilder;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        adherenceRecordReportsController = new AdherenceRecordReportsController(adherenceRecordsReportBuilder);
    }

    @Test
    public void shouldCreateAdherenceRecordReport() throws Exception {
        standaloneSetup(adherenceRecordReportsController).build()
                .perform(get("/adherenceRecords/adherenceReport.xls"))
                .andExpect(status().isOk())
                .andExpect(header().string(AdherenceAuditLogReportsController.CONTENT_DISPOSITION, "inline; filename=adherenceReport.xls"))
                .andExpect(content().contentType(AdherenceAuditLogReportsController.APPLICATION_VND_MS_EXCEL));

        verify(adherenceRecordsReportBuilder).buildAdherenceRecordReport(any(OutputStream.class));
    }

}
