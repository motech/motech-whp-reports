package org.motechproject.whp.reports.webservice.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.whp.reports.contract.AdherenceSubmissionRequest;
import org.motechproject.whp.reports.service.ProviderReminderCallLogService;
import org.springframework.http.MediaType;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class AdherenceSubmissionControllerTest extends ControllerTest {

    @Mock
    ProviderReminderCallLogService providerReminderCallLogService;

    AdherenceSubmissionController controller;

    @Before
    public void setUp() {
        initMocks(this);
        controller = new AdherenceSubmissionController(providerReminderCallLogService);
    }

    @Test
    public void shouldUpdateReminderCallLogIfWithinAdherenceWindow() throws Exception {
        AdherenceSubmissionRequest request = new AdherenceSubmissionRequest();
        request.setProviderId("providerId");

        String requestJson = getJSON(request);

        standaloneSetup(controller).build()
                .perform(post("/adherencesubmission/request").content(requestJson.getBytes()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(providerReminderCallLogService).updateAdherenceStatus(request);
    }
}
