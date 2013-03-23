package org.motechproject.whp.reports.calllog.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.whp.reports.calllog.request.CallLogRequest;
import org.motechproject.whp.reports.calllog.service.CallLogService;

import java.io.IOException;
import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.server.setup.MockMvcBuilders.standaloneSetup;

public class CallLogControllerTest {

    private CallLogController callLogController;

    @Mock
    private CallLogService callLogService;

    @Before
    public void setUp() {
        initMocks(this);
        callLogController = new CallLogController(callLogService);
    }

    @Test
    public void shouldHandleCallLogRequest() throws Exception {
        CallLogRequest callLogRequest = new CallLogRequest();
        String callId = "callId";
        callLogRequest.setCallId(callId);
        callLogRequest.setPhoneNumber("1234567890");
        HashMap<String, String> customData = new HashMap<String, String>();
        String key = "patientId";
        String value = "12345";
        customData.put(key, value);
        callLogRequest.setCustomData(customData);

        String requestJSON = getJSON(callLogRequest);
        standaloneSetup(callLogController)
                .build()
                .perform(
                        post("/callLog/log")
                        .body(requestJSON.getBytes())
                        .contentType(APPLICATION_JSON)
                ).andExpect(status().isOk());

        verify(callLogService).add(callLogRequest);

        assertThat(callLogRequest.getCallId(), is(callId));
        assertThat(callLogRequest.getCustomData().get(key), is(value));
    }

    protected String getJSON(Object object) {
        try{
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writer().writeValueAsString(object);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}