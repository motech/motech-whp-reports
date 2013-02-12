package org.motechproject.whp.reports.export.query.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.whp.reports.export.query.builder.PatientSummaryReportBuilder;

import java.io.OutputStream;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.server.setup.MockMvcBuilders.standaloneSetup;

public class ReportsControllerTest {

    @Mock
    PatientSummaryReportBuilder patientSummaryReportBuilder;

    private ReportsController reportsController;

    @Before
    public void setUp() {
        initMocks(this);
        reportsController = new ReportsController(patientSummaryReportBuilder);
    }

    @Test
    public void shouldInvokeReportBuilderWhenCreatingExcel() throws Exception {

        standaloneSetup(reportsController).build()
                .perform(get("/patientreports/patientSummaryReport.xls"))
                .andExpect(status().isOk())
                .andExpect(header().string(ReportsController.CONTENT_DISPOSITION, "inline; filename=patientSummaryReport.xls"))
                .andExpect(content().type(ReportsController.APPLICATION_VND_MS_EXCEL));

        verify(patientSummaryReportBuilder).build(any(OutputStream.class));
    }
}
