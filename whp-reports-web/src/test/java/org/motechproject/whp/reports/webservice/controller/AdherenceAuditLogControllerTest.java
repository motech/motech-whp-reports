package org.motechproject.whp.reports.webservice.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.whp.reports.contract.adherence.AdherenceAuditLogDTO;
import org.motechproject.whp.reports.service.AdherenceAuditLogService;
import org.springframework.http.MediaType;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.server.setup.MockMvcBuilders.standaloneSetup;

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

        standaloneSetup(adherenceAuditLogController).build()
                .perform(post("/adherence/auditlog").body(requestJSON.getBytes()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(adherenceAuditLogService).save(adherenceAuditLogDTO);
    }
}
