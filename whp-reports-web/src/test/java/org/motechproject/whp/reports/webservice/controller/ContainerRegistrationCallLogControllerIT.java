package org.motechproject.whp.reports.webservice.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.motechproject.whp.reports.contract.ContainerRegistrationCallLogRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.AllOf.allOf;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.server.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:META-INF/spring/applicationContext.xml"})
public class ContainerRegistrationCallLogControllerIT extends ControllerTest {

    @Autowired
    ContainerRegistrationCallLogController containerRegistrationCallLogController;

    @Test
    public void shouldValidateContainerRegistrationCallLogRequest() throws Exception {
        ContainerRegistrationCallLogRequest request;
        request = new ContainerRegistrationCallLogRequest();
        request.setCallId(null);
        request.setDisconnectionType(null);
        request.setStartDateTime(null);
        request.setEndDateTime(null);
        request.setMobileNumber("mobileNumber");
        request.setProviderId(null);

        standaloneSetup(containerRegistrationCallLogController)
                .build()
                .perform(post("/containerRegistrationCallLog/updateCallDetails")
                        .body(getJSON(request).getBytes())
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isInternalServerError()).andExpect(content().string(allOf(
                containsString("format-error.ContainerRegistrationCallLogRequest.callId"),
                containsString("format-error.ContainerRegistrationCallLogRequest.providerId"),
                containsString("format-error.ContainerRegistrationCallLogRequest.mobileNumber"),
                containsString("invalid-data.ContainerRegistrationCallLogRequest.disconnectionType"),
                containsString("invalid-data.ContainerRegistrationCallLogRequest.startDateTime"),
                containsString("invalid-data.ContainerRegistrationCallLogRequest.endDateTime")
        )));
    }
}
