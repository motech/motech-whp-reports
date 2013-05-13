package org.motechproject.whp.reports.webservice.controller.it;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.motechproject.whp.reports.contract.ContainerRegistrationCallDetailsLogRequest;
import org.motechproject.whp.reports.webservice.controller.ContainerRegistrationCallLogController;
import org.motechproject.whp.reports.webservice.controller.ControllerTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.AllOf.allOf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:META-INF/spring/applicationContext.xml"})
public class ContainerRegistrationCallLogControllerIT extends ControllerTest {

    @Autowired
    ContainerRegistrationCallLogController containerRegistrationCallLogController;

    @Test
    public void shouldValidateContainerRegistrationCallLogRequest() throws Exception {
        ContainerRegistrationCallDetailsLogRequest request = new ContainerRegistrationCallDetailsLogRequest();
        request.setCallId(null);
        request.setDisconnectionType(null);
        request.setStartDateTime(null);
        request.setEndDateTime(null);
        request.setMobileNumber("mobileNumber");
        request.setProviderId(null);

        standaloneSetup(containerRegistrationCallLogController)
                .build()
                .perform(post("/containerRegistrationCallLog/updateCallDetails")
                        .content(getJSON(request).getBytes())
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isInternalServerError()).andExpect(content().string(allOf(
                containsString("format-error.ContainerRegistrationCallDetailsLogRequest.callId"),
                containsString("format-error.ContainerRegistrationCallDetailsLogRequest.providerId"),
                containsString("format-error.ContainerRegistrationCallDetailsLogRequest.mobileNumber"),
                containsString("invalid-data.ContainerRegistrationCallDetailsLogRequest.disconnectionType"),
                containsString("invalid-data.ContainerRegistrationCallDetailsLogRequest.startDateTime"),
                containsString("invalid-data.ContainerRegistrationCallDetailsLogRequest.endDateTime")
        )));
    }
}
