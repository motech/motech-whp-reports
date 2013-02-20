package org.motechproject.whp.reports.export.query.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.whp.reports.export.query.builder.PatientReportBuilder;
import org.motechproject.whp.reports.export.query.dao.PatientReportType;
import org.motechproject.whp.reports.export.query.model.PatientReportRequest;

import java.io.OutputStream;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class PatientReportsControllerTest {

    @Mock
    PatientReportBuilder patientReportBuilder;

    private PatientReportsController patientReportsController;

    @Before
    public void setUp() {
        initMocks(this);
        patientReportsController = new PatientReportsController(patientReportBuilder);
    }

    @Test
    public void shouldCreatePatientSummaryReport() throws Exception {

        PatientReportRequest patientReportRequest = new PatientReportRequest();
        patientReportRequest.setDistrict("district");
        patientReportRequest.setFrom("2012-01-01");
        patientReportRequest.setTo("2012-01-31");
        patientReportRequest.setReportType(PatientReportType.SUMMARY_REPORT);

        standaloneSetup(patientReportsController).build()
                .perform(get("/patientreports/patientSummary.xls")
                        .param("district", "district")
                        .param("from", patientReportRequest.getFrom())
                        .param("to", patientReportRequest.getTo()))
                .andExpect(status().isOk())
                .andExpect(header().string(PatientReportsController.CONTENT_DISPOSITION, "inline; filename=patientSummary.xls"))
                .andExpect(content().contentType(PatientReportsController.APPLICATION_VND_MS_EXCEL));

        verify(patientReportBuilder).buildSummaryReport(eq(patientReportRequest), any(OutputStream.class));
    }

    @Test
    public void shouldCreatePatientRegistrationsReport() throws Exception {

        PatientReportRequest patientReportRequest = new PatientReportRequest();
        patientReportRequest.setDistrict("district");
        patientReportRequest.setFrom("2012-01-01");
        patientReportRequest.setTo("2012-01-31");
        patientReportRequest.setReportType(PatientReportType.SUMMARY_REPORT);

        standaloneSetup(patientReportsController).build()
                .perform(get("/patientreports/patientRegistrations.xls")
                        .param("district", "district")
                        .param("from", patientReportRequest.getFrom())
                        .param("to", patientReportRequest.getTo()))
                .andExpect(status().isOk())
                .andExpect(header().string(PatientReportsController.CONTENT_DISPOSITION, "inline; filename=patientRegistrations.xls"))
                .andExpect(content().contentType(PatientReportsController.APPLICATION_VND_MS_EXCEL));

        verify(patientReportBuilder).buildRegistrationsReport(eq(patientReportRequest), any(OutputStream.class));
    }

    @Test
    public void shouldCreatePatientClosedTreatmentReport() throws Exception {

        PatientReportRequest patientReportRequest = new PatientReportRequest();
        patientReportRequest.setDistrict("district");
        patientReportRequest.setFrom("2012-01-01");
        patientReportRequest.setTo("2012-01-31");
        patientReportRequest.setReportType(PatientReportType.CLOSED_TREATMENT);

        standaloneSetup(patientReportsController).build()
                .perform(get("/patientreports/patientClosedTreatments.xls")
                        .param("district", "district")
                        .param("from", patientReportRequest.getFrom())
                        .param("to", patientReportRequest.getTo()))
                .andExpect(status().isOk())
                .andExpect(header().string(PatientReportsController.CONTENT_DISPOSITION, "inline; filename=patientClosedTreatments.xls"))
                .andExpect(content().contentType(PatientReportsController.APPLICATION_VND_MS_EXCEL));

        verify(patientReportBuilder).buildClosedTreatmentsReport(eq(patientReportRequest), any(OutputStream.class));
    }
}
