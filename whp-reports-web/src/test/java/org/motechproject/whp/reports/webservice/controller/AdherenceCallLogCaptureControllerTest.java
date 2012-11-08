package org.motechproject.whp.reports.webservice.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.whp.reports.builder.DomainMapper;
import org.motechproject.whp.reports.contract.AdherenceCallLogRequest;
import org.motechproject.whp.reports.domain.measure.AdherenceCallLog;
import org.motechproject.whp.reports.service.AdherenceCallLogService;
import org.springframework.http.MediaType;

import java.util.Date;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.server.setup.MockMvcBuilders.standaloneSetup;

public class AdherenceCallLogCaptureControllerTest extends ControllerTest {

    @Mock
    private AdherenceCallLogService callLogService;

    private AdherenceCallLogCaptureController controller;
    private DomainMapper domainMapper;

    @Before
    public void setUp() {
        initMocks(this);
        domainMapper = new DomainMapper(null);
        controller = new AdherenceCallLogCaptureController(callLogService, domainMapper);
    }

    @Test
    public void shouldHandleCallLogRequest() throws Exception {
        AdherenceCallLogRequest callLogRequest = new AdherenceCallLogRequest();
        callLogRequest.setCalledBy("caller");
        callLogRequest.setProviderId("providerId");
        callLogRequest.setStartTime(new Date());
        callLogRequest.setEndTime(new Date());

        String requestJson = getJSON(callLogRequest);
        standaloneSetup(controller).build()
                .perform(post("/adherenceCallLog/measure").body(requestJson.getBytes()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(callLogService).save(domainMapper.mapAdherenceCallLog(callLogRequest));
    }

    @Test
    public void shouldRespondWithNotOkayIfServiceThrowsAnException() throws Exception {
        AdherenceCallLogRequest callLogRequest = new AdherenceCallLogRequest();
        String requestJson = getJSON(callLogRequest);

        doThrow(new RuntimeException()).when(callLogService).save(any(AdherenceCallLog.class));
        standaloneSetup(controller).build()
                .perform(post("/adherenceCallLog/measure")
                        .body(requestJson.getBytes())
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isInternalServerError());
    }
}
