package org.motechproject.whp.reports.webservice.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.whp.reports.contract.query.PatientAdherenceSummary;
import org.motechproject.whp.reports.service.PatientAdherenceDataService;
import org.springframework.http.MediaType;

import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class PatientAdherenceSummaryControllerTest extends ControllerTest {

    PatientAdherenceSummaryController patientAdherenceSummaryController;

    @Mock
    PatientAdherenceDataService patientAdherenceDataService;

    @Before
    public void setUp() {
        initMocks(this);
        patientAdherenceSummaryController = new PatientAdherenceSummaryController(patientAdherenceDataService);
    }

    @Test
    public void shouldReturnAdherenceSummaries() throws Exception {
        int limit = 100;
        int skip = 0;

        List<PatientAdherenceSummary> adherenceSummaryList = asList(new PatientAdherenceSummary("p1", "m1", 1), new PatientAdherenceSummary("p2", "m2", 2));

        when(patientAdherenceDataService.getPatientsWithMissingAdherence(skip, limit)).thenReturn(adherenceSummaryList);

        standaloneSetup(patientAdherenceSummaryController).build()
                .perform(get("/patientsWithoutAdherence")
                        .param("skip", String.valueOf(skip))
                        .param("limit", String.valueOf(limit))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(getJSON(adherenceSummaryList)));
    }
}
