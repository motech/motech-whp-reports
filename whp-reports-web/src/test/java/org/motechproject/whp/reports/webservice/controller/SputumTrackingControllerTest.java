package org.motechproject.whp.reports.webservice.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.whp.reports.contract.*;
import org.motechproject.whp.reports.service.ContainerRecordService;
import org.springframework.http.MediaType;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.motechproject.whp.reports.contract.builder.ContainerRegistrationRequestBuilder.defaultRequest;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class SputumTrackingControllerTest extends ControllerTest{

    @Mock
    private ContainerRecordService containerRecordService;
    private SputumTrackingController sputumTrackingController;

    @Before
    public void setUp(){
        initMocks(this);
        sputumTrackingController = new SputumTrackingController(containerRecordService);
    }

    @Test
    public void shouldRegisterContainer() throws Exception {
        ContainerRegistrationReportingRequest request = defaultRequest();
        String requestJSON = getJSON(request);

        standaloneSetup(sputumTrackingController).build()
                        .perform(post("/sputumTracking/containerRegistrationMeasure").content(requestJSON.getBytes()).contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());

        verify(containerRecordService).recordContainerRegistration(request);
    }

    @Test
    public void shouldCaptureLabResults() throws Exception {
        SputumLabResultsCaptureReportingRequest request = new SputumLabResultsCaptureReportingRequest();

        String requestJSON = getJSON(request);

        standaloneSetup(sputumTrackingController).build()
                .perform(post("/sputumTracking/sputumLabResultsMeasure").content(requestJSON.getBytes()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldUpdateContainerStatus() throws Exception {
        ContainerStatusReportingRequest request = new ContainerStatusReportingRequest();

        String requestJSON = getJSON(request);

        standaloneSetup(sputumTrackingController).build()
                .perform(post("/sputumTracking/containerStatusMeasure").content(requestJSON.getBytes()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldUpdateContainerPatientMapping() throws Exception {
        ContainerPatientMappingReportingRequest request = new ContainerPatientMappingReportingRequest();

        String requestJSON = getJSON(request);

        standaloneSetup(sputumTrackingController).build()
                .perform(post("/sputumTracking/containerPatientMappingMeasure").content(requestJSON.getBytes()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldUpdateUserGivenPatientDetails() throws Exception {
        UserGivenPatientDetailsReportingRequest request = new UserGivenPatientDetailsReportingRequest();

        String requestJSON = getJSON(request);

        standaloneSetup(sputumTrackingController).build()
                .perform(post("/sputumTracking/updateUserGivenPatientDetails").content(requestJSON.getBytes()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(containerRecordService).updateContainerUserGivenDetails(request);
    }

    @Test
    public void shouldDeleteUserGivenPatientDetails() throws Exception {
        UserGivenPatientDetailsReportingRequest request = new UserGivenPatientDetailsReportingRequest();

        String requestJSON = getJSON(request);

        standaloneSetup(sputumTrackingController).build()
                .perform(post("/sputumTracking/deleteUserGivenPatientDetails").content(requestJSON.getBytes()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(containerRecordService).deleteContainerUserGivenDetails(request);
    }
}
