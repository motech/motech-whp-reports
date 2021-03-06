package org.motechproject.whp.reports.webservice.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.whp.reports.builder.DomainMapper;
import org.motechproject.whp.reports.contract.AdherenceCallLogRequest;
import org.motechproject.whp.reports.contract.AdherenceCallStatusRequest;
import org.motechproject.whp.reports.domain.measure.calllog.AdherenceCallLog;
import org.motechproject.whp.reports.service.AdherenceCallLogService;
import org.springframework.http.MediaType;

import java.util.Date;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class AdherenceCallLogCaptureControllerTest extends ControllerTest {

    @Mock
    private AdherenceCallLogService callLogService;

    private AdherenceCallLogCaptureController controller;
    private DomainMapper domainMapper;

    @Before
    public void setUp() {
        initMocks(this);
        domainMapper = new DomainMapper();
        controller = new AdherenceCallLogCaptureController(callLogService, domainMapper);
    }

    @Test
    public void shouldHandleCallLogRequest() throws Exception {
        AdherenceCallLogRequest callLogRequest = new AdherenceCallLogRequest();
        callLogRequest.setProviderId("providerId");
        callLogRequest.setStartTime(new Date());
        callLogRequest.setEndTime(new Date());
        callLogRequest.setAttemptTime(new Date());
        callLogRequest.setFlashingCallId("flashingCallId");

        String requestJson = getJSON(callLogRequest);
        standaloneSetup(controller).build()
                .perform(post("/adherenceCallLog/measure").content(requestJson.getBytes()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(callLogService).save(domainMapper.mapAdherenceCallLog(callLogRequest));
    }

    @Test
    public void shouldHandleCallStatusRequest() throws Exception {
        AdherenceCallStatusRequest request = new AdherenceCallStatusRequest();
        request.setAdherenceCaptured("1");
        request.setAdherenceNotCaptured("1");
        request.setAttemptTime("22/12/2012 10:10:10");
        request.setCallAnswered("YES");
        request.setCallId("callid");
        request.setCallStatus("Answered");
        request.setDisconnectionType("");
        request.setEndTime("22/12/2012 10:10:10");
        request.setFlashingCallId("flashingId");
        request.setProviderId("providerid");
        request.setStartTime("22/12/2012 10:10:10");
        request.setTotalPatients("2");

        String requestJson = getJSON(request);
        standaloneSetup(controller).build()
                .perform(post("/adherenceCallLog/status/measure").content(requestJson.getBytes()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(callLogService).save(domainMapper.mapAdherenceCallLog(request.toCallLogRequest()));
    }

    @Test
    public void shouldRespondWithNotOkayIfServiceThrowsAnException() throws Exception {
        AdherenceCallLogRequest callLogRequest = new AdherenceCallLogRequest();
        String requestJson = getJSON(callLogRequest);

        doThrow(new RuntimeException()).when(callLogService).save(any(AdherenceCallLog.class));
        standaloneSetup(controller).build()
                .perform(post("/adherenceCallLog/measure")
                        .content(requestJson.getBytes())
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void shouldRespondWithNotOkayWhenAdherenceCallStatusValidationFails() throws Exception {
        AdherenceCallStatusRequest invalidRequest = new AdherenceCallStatusRequest();

        String requestJson = getJSON(invalidRequest);
        standaloneSetup(controller).build()
                .perform(post("/adherenceCallLog/status/measure").content(requestJson.getBytes()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
