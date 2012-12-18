package org.motechproject.whp.reports.webservice.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.motechproject.whp.reports.contract.ProviderReminderCallLogRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.internal.matchers.StringContains.containsString;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.server.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:META-INF/spring/applicationContext.xml"})
public class ProviderReminderCallLogControllerIT extends ControllerTest {

    @Autowired
    ProviderReminderCallLogController providerReminderCallLogController;

    @Test
    public void shouldValidateContainerRegistrationCallLogRequest() throws Exception {
        ProviderReminderCallLogRequest request = new ProviderReminderCallLogRequest();

        standaloneSetup(providerReminderCallLogController)
                .build()
                .perform(post("/providerReminderCallLog/measure")
                        .body(getJSON(request).getBytes())
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("requestId")))
                .andExpect(content().string(containsString("callId")))
                .andExpect(content().string(containsString("reminderType")))
                .andExpect(content().string(containsString("msisdn")))
                .andExpect(content().string(containsString("providerId")))
                .andExpect(content().string(containsString("callStatus")))
                .andExpect(content().string(containsString("disconnectionType")))
                .andExpect(content().string(containsString("startTime")))
                .andExpect(content().string(containsString("endTime")))
                .andExpect(content().string(containsString("attempt")))
                .andExpect(content().string(containsString("attemptTime")));
    }
}
