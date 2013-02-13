package org.motechproject.whp.reports.export.query.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.whp.reports.export.query.builder.PatientSummaryReportBuilder;
import org.motechproject.whp.reports.export.query.model.PatientReportRequest;

import java.io.OutputStream;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.server.setup.MockMvcBuilders.standaloneSetup;

public class PatientReportsControllerTest {

    @Mock
    PatientSummaryReportBuilder patientSummaryReportBuilder;

    private PatientReportsController patientReportsController;

    @Before
    public void setUp() {
        initMocks(this);
        patientReportsController = new PatientReportsController(patientSummaryReportBuilder);
    }

    @Test
    public void shouldInvokeReportBuilderWhenCreatingExcel() throws Exception {

        PatientReportRequest patientReportRequest = new PatientReportRequest();
        patientReportRequest.setDistrict("district");
        patientReportRequest.setTbRegistrationDateFrom("2012-01-01");
        patientReportRequest.setTbRegistrationDateTo("2012-01-31");

        standaloneSetup(patientReportsController).build()
                .perform(get("/patientreports/patientSummaryReport.xls")
                        .param("district", "district")
                        .param("tbRegistrationDateFrom", patientReportRequest.getTbRegistrationDateFrom())
                        .param("tbRegistrationDateTo", patientReportRequest.getTbRegistrationDateTo()))
                .andExpect(status().isOk())
                .andExpect(header().string(PatientReportsController.CONTENT_DISPOSITION, "inline; filename=patientSummaryReport.xls"))
                .andExpect(content().type(PatientReportsController.APPLICATION_VND_MS_EXCEL));

        verify(patientSummaryReportBuilder).build(eq(patientReportRequest), any(OutputStream.class));
    }
}
