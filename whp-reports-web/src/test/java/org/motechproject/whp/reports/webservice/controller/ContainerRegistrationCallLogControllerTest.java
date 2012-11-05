package org.motechproject.whp.reports.webservice.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.whp.reports.builder.DomainMapper;
import org.motechproject.whp.reports.contract.ContainerRegistrationCallLogRequest;
import org.motechproject.whp.reports.service.ContainerRegistrationCallLogService;
import org.springframework.http.MediaType;

import java.util.Date;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.server.setup.MockMvcBuilders.standaloneSetup;

public class ContainerRegistrationCallLogControllerTest extends ControllerTest {

    private ContainerRegistrationCallLogController controller;
    @Mock
    private ContainerRegistrationCallLogService containerRegistrationCallLogService;
    private DomainMapper domainMapper;

    @Before
    public void setUp() {
        initMocks(this);
        domainMapper = new DomainMapper();
        controller = new ContainerRegistrationCallLogController(containerRegistrationCallLogService, domainMapper);
    }

    @Test
    public void shouldCreateContainerRegistrationCallLog() throws Exception {
        ContainerRegistrationCallLogRequest request;
        request = new ContainerRegistrationCallLogRequest();
        request.setCallId("callId");
        request.setDisconnectionType("disconnectionType");
        request.setStartDateTime(new Date());
        request.setEndDateTime(new Date());
        request.setMobileNumber("mobileNumber");
        request.setProviderId("providerId");


        standaloneSetup(controller)
                .build()
                .perform(post("/containerRegistrationCallLog/create")
                        .body(getJSON(request).getBytes())
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());

        verify(containerRegistrationCallLogService).save(domainMapper.mapContainerRegistrationCallLog(request));
    }
}
