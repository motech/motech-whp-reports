package org.motechproject.whp.reports.webservice.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.whp.reports.service.DistrictService;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class HomeControllerTest {

    HomeController homeController;

    @Mock
    DistrictService districtService;

    @Before
    public void setUp(){
        initMocks(this);
        homeController = new HomeController(districtService);
    }

    @Test
    public void shouldReturnAllDistrictsOnReportsPageLoad() throws Exception {
        List districts = mock(List.class);
        when(districtService.getAllDistricts()).thenReturn(districts);
        standaloneSetup(homeController)
                .build()
                .perform(get("/patientReportsFilter"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("districts", districts))
                .andExpect(forwardedUrl("reports/patientReportsFilter"));

        verify(districtService).getAllDistricts();
    }

    @Test
    public void shouldProvideDistrictsToContainerReportsPageLoad() throws Exception {
        List districts = mock(List.class);
        when(districtService.getAllDistricts()).thenReturn(districts);
        standaloneSetup(homeController)
                .build()
                .perform(get("/containerReports"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("districts", districts))
                .andExpect(forwardedUrl("dashboard/dashboardFilter"));

        verify(districtService).getAllDistricts();
    }

    @Test
    public void shouldReturnAllDistrictsOnTbRegistrationsPageLoad() throws Exception {
        List districts = mock(List.class);
        when(districtService.getAllDistricts()).thenReturn(districts);
        standaloneSetup(homeController)
                .build()
                .perform(get("/dashboard/tbRegistration"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("districts", districts))
                .andExpect(forwardedUrl("dashboard/tbRegistration"));

        verify(districtService).getAllDistricts();
    }
}
