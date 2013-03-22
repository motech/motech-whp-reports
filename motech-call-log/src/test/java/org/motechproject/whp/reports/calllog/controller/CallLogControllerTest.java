package org.motechproject.whp.reports.calllog.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.whp.reports.calllog.request.CallLogRequest;
import org.motechproject.whp.reports.calllog.service.CallLogService;
import org.springframework.http.MediaType;

import java.io.IOException;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

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
        callLogRequest.setCallId("1234");
        callLogRequest.setPhoneNumber("1234567890");

        String requestJson = getJSON(callLogRequest);

        standaloneSetup(callLogController)
                .build()
                .perform(post("/callLog/log")
                        .content(requestJson.getBytes())
                        .contentType(MediaType.APPLICATION_JSON)
                )
        .andExpect(status().isOk());

        verify(callLogService).add(callLogRequest);
    }

    private String getJSON(Object object) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writer().writeValueAsString(object);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
