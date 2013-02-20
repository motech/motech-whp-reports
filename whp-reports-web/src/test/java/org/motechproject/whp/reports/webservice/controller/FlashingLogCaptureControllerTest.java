package org.motechproject.whp.reports.webservice.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.whp.reports.builder.DomainMapper;
import org.motechproject.whp.reports.contract.FlashingLogRequest;
import org.motechproject.whp.reports.domain.measure.FlashingLog;
import org.motechproject.whp.reports.service.FlashingLogService;
import org.springframework.http.MediaType;

import java.util.Date;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class FlashingLogCaptureControllerTest extends ControllerTest{

    @Mock
    private FlashingLogService flashingLogService;

    private FlashingLogCaptureController controller;
    private DomainMapper domainMapper;

    @Before
    public void setUp() {
        initMocks(this);
        domainMapper = new DomainMapper();
        controller = new FlashingLogCaptureController(flashingLogService, domainMapper);
    }

    @Test
    public void shouldHandleFlashingLogRequest() throws Exception {
        FlashingLogRequest flashingLogRequest = new FlashingLogRequest();
        flashingLogRequest.setMobileNumber("1234567890");
        flashingLogRequest.setProviderId("providerId");
        flashingLogRequest.setCallTime(new Date());
        flashingLogRequest.setCreationTime(new Date());

        String requestJson = getJSON(flashingLogRequest);
        standaloneSetup(controller).build()
                .perform(post("/flashingLog/measure").content(requestJson.getBytes()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(flashingLogService).save(domainMapper.buildFlashingRequestLog(flashingLogRequest));
    }

    @Test
    public void shouldRespondWithNotOkayIfServiceThrowsAnException() throws Exception {
        FlashingLogRequest flashingLogRequest = new FlashingLogRequest();
        String requestJson = getJSON(flashingLogRequest);

        doThrow(new RuntimeException()).when(flashingLogService).save(any(FlashingLog.class));
        standaloneSetup(controller).build()
                .perform(post("/flashingLog/measure")
                        .content(requestJson.getBytes())
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isInternalServerError());
    }

}
