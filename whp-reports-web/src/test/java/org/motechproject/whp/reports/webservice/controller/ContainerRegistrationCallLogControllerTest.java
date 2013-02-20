package org.motechproject.whp.reports.webservice.controller;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.validation.validator.BeanValidator;
import org.motechproject.whp.reports.builder.ContainerRegistrationCallLogMapper;
import org.motechproject.whp.reports.contract.ContainerRegistrationCallDetailsLogRequest;
import org.motechproject.whp.reports.contract.ContainerVerificationLogRequest;
import org.motechproject.whp.reports.contract.ProviderVerificationLogRequest;
import org.motechproject.whp.reports.domain.measure.ContainerRegistrationCallLog;
import org.motechproject.whp.reports.repository.ContainerRegistrationCallLogRepository;
import org.motechproject.whp.reports.service.ContainerRegistrationCallLogService;
import org.springframework.http.MediaType;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class ContainerRegistrationCallLogControllerTest extends ControllerTest {

    private ContainerRegistrationCallLogController controller;
    @Mock
    private ContainerRegistrationCallLogService containerRegistrationCallLogService;
    @Mock
    private ContainerRegistrationCallLogRepository containerRegistrationCallLogRepository;
    @Mock
    private BeanValidator beanValidator;
    @Mock
    private ContainerRegistrationCallLogMapper containerRegistrationCallLogMapper;

    @Before
    public void setUp() {
        initMocks(this);
        controller = new ContainerRegistrationCallLogController(containerRegistrationCallLogService, containerRegistrationCallLogMapper, beanValidator);
    }

    @Test
    public void shouldCreateContainerRegistrationCallLog() throws Exception {
        ContainerRegistrationCallDetailsLogRequest request = new ContainerRegistrationCallDetailsLogRequest();
        request.setCallId("callId");
        request.setDisconnectionType("disconnectionType");
        request.setStartDateTime("10/12/2012 12:32:35");
        request.setEndDateTime("10/12/2012 12:33:35");
        request.setMobileNumber("mobileNumber");
        request.setProviderId("providerId");

        ContainerRegistrationCallLog expectedCallLog = new ContainerRegistrationCallLog();
        when(containerRegistrationCallLogMapper.mapFromCallDetails(request)).thenReturn(expectedCallLog);

        standaloneSetup(controller)
                .build()
                .perform(post("/containerRegistrationCallLog/updateCallDetails")
                        .content(getJSON(request).getBytes())
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());

        verify(containerRegistrationCallLogService).save(expectedCallLog);
    }

    @Test
    public void shouldHandleProviderVerificationLogRequest() throws Exception {
        ProviderVerificationLogRequest request = new ProviderVerificationLogRequest();
        request.setCallId("callId");
        request.setMobileNumber("mobileNumber");
        request.setTime(DateTime.now());

        ContainerRegistrationCallLog expectedCallLog = new ContainerRegistrationCallLog();
        when(containerRegistrationCallLogMapper.mapFromProviderVerificationDetails(any(ProviderVerificationLogRequest.class))).thenReturn(expectedCallLog);

        standaloneSetup(controller)
                .build()
                .perform(post("/containerRegistrationCallLog/providerVerification")
                        .content(getJSON(request).getBytes())
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());

        verify(containerRegistrationCallLogService).save(expectedCallLog);
    }

    @Test
    public void shouldHandleContainerVerificationLogRequest() throws Exception {
        ContainerVerificationLogRequest request = new ContainerVerificationLogRequest();
        request.setCallId("callId");
        request.setMobileNumber("mobileNumber");
        request.setValidContainer(true);

        ContainerRegistrationCallLog expectedCallLog = new ContainerRegistrationCallLog();
        when(containerRegistrationCallLogMapper.mapFromContainerVerificationDetails(request)).thenReturn(expectedCallLog);

        standaloneSetup(controller)
                .build()
                .perform(post("/containerRegistrationCallLog/containerVerification")
                        .content(getJSON(request).getBytes())
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());

        verify(containerRegistrationCallLogService).save(expectedCallLog);
    }
}
