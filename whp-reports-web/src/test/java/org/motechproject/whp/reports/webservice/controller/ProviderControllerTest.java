package org.motechproject.whp.reports.webservice.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.whp.reports.contract.provider.ProviderDTO;
import org.motechproject.whp.reports.service.ProviderService;
import org.springframework.http.MediaType;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.server.setup.MockMvcBuilders.standaloneSetup;

public class ProviderControllerTest extends ControllerTest{

    ProviderController providerController;

    @Mock
    ProviderService providerService;

    @Before
    public void setUp() {
        initMocks(this);
        providerController = new ProviderController(providerService);
    }

    @Test
    public void shouldSaveProvider() throws Exception {
        ProviderDTO providerDTO = new ProviderDTO();
        String requestJSON = getJSON(providerDTO);

        standaloneSetup(providerController).build()
                .perform(post("/provider/update").body(requestJSON.getBytes()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(providerService).save(providerDTO);
    }
}
