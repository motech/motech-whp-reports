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
    public void shouldCreatePatientSummaryReport() throws Exception {

        PatientReportRequest patientReportRequest = new PatientReportRequest();
        patientReportRequest.setDistrict("district");
        patientReportRequest.setFrom("2012-01-01");
        patientReportRequest.setTo("2012-01-31");

        standaloneSetup(patientReportsController).build()
                .perform(get("/patientreports/patientSummary.xls")
                        .param("district", "district")
                        .param("from", patientReportRequest.getFrom())
                        .param("to", patientReportRequest.getTo()))
                .andExpect(status().isOk())
                .andExpect(header().string(PatientReportsController.CONTENT_DISPOSITION, "inline; filename=patientSummary.xls"))
                .andExpect(content().type(PatientReportsController.APPLICATION_VND_MS_EXCEL));

        verify(patientSummaryReportBuilder).buildSummaryReport(eq(patientReportRequest), any(OutputStream.class));
    }

    @Test
    public void shouldCreatePatientRegistrationsReport() throws Exception {

        PatientReportRequest patientReportRequest = new PatientReportRequest();
        patientReportRequest.setDistrict("district");
        patientReportRequest.setFrom("2012-01-01");
        patientReportRequest.setTo("2012-01-31");

        standaloneSetup(patientReportsController).build()
                .perform(get("/patientreports/patientRegistrations.xls")
                        .param("district", "district")
                        .param("from", patientReportRequest.getFrom())
                        .param("to", patientReportRequest.getTo()))
                .andExpect(status().isOk())
                .andExpect(header().string(PatientReportsController.CONTENT_DISPOSITION, "inline; filename=patientRegistrations.xls"))
                .andExpect(content().type(PatientReportsController.APPLICATION_VND_MS_EXCEL));

        verify(patientSummaryReportBuilder).buildRegistrationsReport(eq(patientReportRequest), any(OutputStream.class));
    }
}
