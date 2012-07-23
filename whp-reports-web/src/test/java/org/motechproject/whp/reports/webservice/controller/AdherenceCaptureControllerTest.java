package org.motechproject.whp.reports.webservice.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.motechproject.whp.reports.webservice.request.AdherenceCaptureRequest;
import org.springframework.http.MediaType;

import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.server.setup.MockMvcBuilders.standaloneSetup;

public class AdherenceCaptureControllerTest {

    @Test
    public void shouldHandleAdherenceCaptureRequest() throws Exception {
        AdherenceCaptureController adherenceCaptureController = new AdherenceCaptureController();
        AdherenceCaptureRequest request = new AdherenceCaptureRequest("providerId", "patientId", 3);

        ObjectMapper mapper = new ObjectMapper();
        String requestJson = mapper.writer().writeValueAsString(request);
        standaloneSetup(adherenceCaptureController).build()
                .perform(post("/adherence/capture").body(requestJson.getBytes()).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
    }
}
