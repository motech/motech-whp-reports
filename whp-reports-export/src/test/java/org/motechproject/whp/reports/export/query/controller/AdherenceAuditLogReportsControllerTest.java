package org.motechproject.whp.reports.export.query.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.whp.reports.export.query.builder.AdherenceAuditLogReportBuilder;

import java.io.OutputStream;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


public class AdherenceAuditLogReportsControllerTest {


    @Mock
    AdherenceAuditLogReportBuilder adherenceAuditLogReportBuilder;

    private AdherenceAuditLogReportsController adherenceAuditLogReportsController;

    @Before
    public void setUp() {
        initMocks(this);
        adherenceAuditLogReportsController = new AdherenceAuditLogReportsController(adherenceAuditLogReportBuilder);
    }

    @Test
    public void shouldCreateAdherenceAuditLogReport() throws Exception {

        standaloneSetup(adherenceAuditLogReportsController).build()
                .perform(get("/auditreports/adherenceAuditLogReport.xls"))
                        .andExpect(status().isOk())
                        .andExpect(header().string(AdherenceRecordReportsController.CONTENT_DISPOSITION, "inline; filename=adherenceAuditLogReport.xls"))
                        .andExpect(content().contentType(AdherenceAuditLogReportsController.APPLICATION_VND_MS_EXCEL));

        verify(adherenceAuditLogReportBuilder).buildAdherenceAuditLogReport(any(OutputStream.class));
    }
}
