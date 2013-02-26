package org.motechproject.whp.reports.webservice.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.whp.reports.contract.adherence.ProviderAdherenceSummaries;
import org.motechproject.whp.reports.contract.adherence.ProviderAdherenceSummary;
import org.motechproject.whp.reports.service.ProviderAdherenceDataService;
import org.springframework.http.MediaType;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class ProviderAdherenceSummaryControllerTest extends ControllerTest{

    ProviderAdherenceSummaryController providerAdherenceSummaryController;

    @Mock
    ProviderAdherenceDataService providerAdherenceDataService;

    @Before
    public void setUp() {
        initMocks(this);
        providerAdherenceSummaryController = new ProviderAdherenceSummaryController(providerAdherenceDataService);
    }

    @Test
    public void shouldReturnProviderAdherenceSummaryForGivenDistrict() throws Exception {
        String district = "district";

        ProviderAdherenceSummaries adherenceSummaries  = new ProviderAdherenceSummaries(district, new ArrayList<ProviderAdherenceSummary>());

        when(providerAdherenceDataService.getAdherenceSummary(district)).thenReturn(adherenceSummaries);

        standaloneSetup(providerAdherenceSummaryController).build()
                .perform(get("/providerAdherenceSummary/" + district).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(getJSON(adherenceSummaries)));
    }
}
