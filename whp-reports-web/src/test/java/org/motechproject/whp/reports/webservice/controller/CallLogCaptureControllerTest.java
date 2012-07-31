package org.motechproject.whp.reports.webservice.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.whp.reports.domain.measure.CallLog;
import org.motechproject.whp.reports.service.CallLogService;
import org.motechproject.whp.reports.webservice.request.CallLogRequest;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.util.Date;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.server.setup.MockMvcBuilders.standaloneSetup;

public class CallLogCaptureControllerTest {

    @Mock
    private CallLogService callLogService;

    private CallLogCaptureController controller;

    @Before
    public void setUp() {
        initMocks(this);
        controller = new CallLogCaptureController(callLogService);
    }

    @Test
    public void shouldHandleCallLogRequest() throws Exception {
        CallLogRequest callLogRequest = new CallLogRequest();
        callLogRequest.setCalledBy("caller");
        callLogRequest.setProviderId("providerId");
        callLogRequest.setStartTime(new Date());
        callLogRequest.setEndTime(new Date());

        String requestJson = getJSON(callLogRequest);
        standaloneSetup(controller).build()
                .perform(post("/calllog/measure").body(requestJson.getBytes()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(callLogService).save(callLogRequest.createCallLog());
    }

    @Test
    public void shouldRespondWithNotOkayIfServiceThrowsAnException() throws Exception {
        CallLogRequest callLogRequest = new CallLogRequest();
        String requestJson = getJSON(callLogRequest);

        doThrow(new RuntimeException()).when(callLogService).save(any(CallLog.class));
        standaloneSetup(controller).build()
                .perform(post("/calllog/measure")
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
