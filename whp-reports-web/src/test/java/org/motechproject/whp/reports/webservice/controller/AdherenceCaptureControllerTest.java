package org.motechproject.whp.reports.webservice.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.whp.reports.builder.DomainMapper;
import org.motechproject.whp.reports.contract.AdherenceCaptureRequest;
import org.motechproject.whp.reports.domain.measure.PatientAdherenceSubmission;
import org.motechproject.whp.reports.service.PatientAdherenceSubmissionService;
import org.springframework.http.MediaType;

import java.io.IOException;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.server.setup.MockMvcBuilders.standaloneSetup;

public class AdherenceCaptureControllerTest {

    @Mock
    private PatientAdherenceSubmissionService adherenceSubmissionService;

    private AdherenceCaptureController controller;
    private DomainMapper domainMapper;

    @Before
    public void setUp() {
        initMocks(this);
        domainMapper = new DomainMapper(null);
        controller = new AdherenceCaptureController(adherenceSubmissionService, domainMapper);
    }

    @Test
    public void shouldCaptureAdherenceSubmission() throws Exception {
        AdherenceCaptureRequest request = new AdherenceCaptureRequest();
        request.setCallId("callId");

        String requestJson = getJSON(request);
        standaloneSetup(controller).build()
                .perform(post("/adherence/measure").body(requestJson.getBytes()).contentType(MediaType.APPLICATION_JSON))
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
                        .body(requestJson.getBytes())
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isInternalServerError());
    }

    private String getJSON(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writer().writeValueAsString(object);
    }
}
