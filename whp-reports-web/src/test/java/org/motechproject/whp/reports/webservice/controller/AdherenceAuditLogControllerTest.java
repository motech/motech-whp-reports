package org.motechproject.whp.reports.webservice.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.whp.reports.contract.adherence.AdherenceAuditLogDTO;
import org.motechproject.whp.reports.service.AdherenceAuditLogService;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AdherenceAuditLogControllerTest extends ControllerTest{

    AdherenceAuditLogController adherenceAuditLogController;

    @Mock
    AdherenceAuditLogService adherenceAuditLogService;

    @Before
    public void setUp() {
        initMocks(this);
        adherenceAuditLogController = new AdherenceAuditLogController(adherenceAuditLogService);
    }

    @Test
    public void shouldHandleAdherenceAuditLogRequest() throws Exception {

        AdherenceAuditLogDTO adherenceAuditLogDTO = new AdherenceAuditLogDTO();

        String requestJSON = getJSON(adherenceAuditLogDTO);

        MockMvcBuilders.standaloneSetup(adherenceAuditLogController).build()
                .perform(post("/adherence/auditlog").content(requestJSON.getBytes()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(adherenceAuditLogService).save(adherenceAuditLogDTO);
    }
}
