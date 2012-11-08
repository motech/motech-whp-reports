package org.motechproject.whp.reports.webservice.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.validation.validator.BeanValidator;
import org.motechproject.whp.reports.builder.ContainerRegistrationCallLogMapper;
import org.motechproject.whp.reports.contract.ContainerRegistrationCallLogRequest;
import org.motechproject.whp.reports.repository.ContainerRegistrationCallLogRepository;
import org.motechproject.whp.reports.service.ContainerRegistrationCallLogService;
import org.springframework.http.MediaType;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.server.setup.MockMvcBuilders.standaloneSetup;

public class ContainerRegistrationCallLogControllerTest extends ControllerTest {

    private ContainerRegistrationCallLogController controller;
    @Mock
    private ContainerRegistrationCallLogService containerRegistrationCallLogService;
    @Mock
    private ContainerRegistrationCallLogRepository containerRegistrationCallLogRepository;
    @Mock
    private BeanValidator beanValidator;
    private ContainerRegistrationCallLogMapper containerRegistrationCallLogMapper;

    @Before
    public void setUp() {
        initMocks(this);
        containerRegistrationCallLogMapper = new ContainerRegistrationCallLogMapper(containerRegistrationCallLogRepository);
        controller = new ContainerRegistrationCallLogController(containerRegistrationCallLogService, containerRegistrationCallLogMapper, beanValidator);
    }

    @Test
    public void shouldCreateContainerRegistrationCallLog() throws Exception {
        ContainerRegistrationCallLogRequest request;
        request = new ContainerRegistrationCallLogRequest();
        request.setCallId("callId");
        request.setDisconnectionType("disconnectionType");
        request.setStartDateTime("10/12/2012 12:32:35");
        request.setEndDateTime("10/12/2012 12:33:35");
        request.setMobileNumber("mobileNumber");
        request.setProviderId("providerId");


        standaloneSetup(controller)
                .build()
                .perform(post("/containerRegistrationCallLog/updateCallDetails")
                        .body(getJSON(request).getBytes())
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());

        verify(containerRegistrationCallLogService).save(containerRegistrationCallLogMapper.mapContainerRegistrationCallLog(request));
    }
}
