package org.motechproject.whp.reports.webservice.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.whp.reports.contract.ContainerRegistrationRequest;
import org.motechproject.whp.reports.service.SputumTrackingService;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.util.Date;

import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.server.setup.MockMvcBuilders.standaloneSetup;

public class SputumTrackingControllerTest {

    @Mock
    private SputumTrackingService sputumTrackingService;
    private SputumTrackingController sputumTrackingController;

    @Before
    public void setUp(){
        initMocks(this);
        sputumTrackingController = new SputumTrackingController(sputumTrackingService);
    }

    @Test
    public void shouldRegisterContainer() throws Exception {

        ContainerRegistrationRequest containerRegistrationRequest = new ContainerRegistrationRequest();
        containerRegistrationRequest.setContainerId("containerId");
        containerRegistrationRequest.setInstance("PreTreatment");
        containerRegistrationRequest.setDateIssuedOn(new Date());
        containerRegistrationRequest.setProviderId("raj");
        containerRegistrationRequest.setSubmitterRole("CmfAdmin");
        containerRegistrationRequest.setSubmitterId("submitterId");

        String requestJSON = getJSON(containerRegistrationRequest);

        standaloneSetup(sputumTrackingController).build()
                        .perform(post("/sputumTracking/containerRegistrationMeasure").body(requestJSON.getBytes()).contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
    }


    private String getJSON(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writer().writeValueAsString(object);
    }
}
