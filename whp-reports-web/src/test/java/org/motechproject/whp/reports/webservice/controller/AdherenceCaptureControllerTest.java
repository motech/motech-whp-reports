package org.motechproject.whp.reports.webservice.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.whp.reports.builder.DomainMapper;
import org.motechproject.whp.reports.contract.AdherenceCaptureRequest;
import org.motechproject.whp.reports.domain.measure.PatientAdherenceSubmission;
import org.motechproject.whp.reports.service.PatientAdherenceSubmissionService;
import org.springframework.http.MediaType;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class AdherenceCaptureControllerTest extends ControllerTest{

    @Mock
    private PatientAdherenceSubmissionService adherenceSubmissionService;

    private AdherenceCaptureController controller;
    private DomainMapper domainMapper;

    @Before
    public void setUp() {
        initMocks(this);
        domainMapper = new DomainMapper();
        controller = new AdherenceCaptureController(adherenceSubmissionService, domainMapper);
    }

    @Test
    public void shouldCaptureAdherenceSubmission() throws Exception {
        AdherenceCaptureRequest request = new AdherenceCaptureRequest();
        request.setCallId("callId");

        String requestJson = getJSON(request);
        standaloneSetup(controller).build()
                .perform(post("/adherence/measure").content(requestJson.getBytes()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(adherenceSubmissionService).save(domainMapper.mapAdherenceSubmission(request));
    }

    @Test
    public void shouldRespondWithNotOkayIfServiceThrowsAnException() throws Exception {
        AdherenceCaptureRequest request = new AdherenceCaptureRequest();
        request.setCallId("callId");
        String requestJson = getJSON(request);

        doThrow(new RuntimeException()).when(adherenceSubmissionService).save(any(PatientAdherenceSubmission.class));
        standaloneSetup(controller).build()
                .perform(post("/adherence/measure")
                        .content(requestJson.getBytes())
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isInternalServerError());
    }


    @Test
    public void shouldCaptureAdherenceDeletion() throws Exception {
        String patientId = "patientId";

        String requestJson = getJSON(patientId);
        standaloneSetup(controller).build()
                .perform(post("/adherence/measureDelete").content(requestJson.getBytes()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(adherenceSubmissionService).delete(requestJson);
    }
}
