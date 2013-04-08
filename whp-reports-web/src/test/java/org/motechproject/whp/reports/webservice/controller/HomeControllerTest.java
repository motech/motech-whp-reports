package org.motechproject.whp.reports.webservice.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.whp.reports.service.DistrictService;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
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
        standaloneSetup(homeController)
                .build()
                .perform(get("/reportsFilter").param("reportType", "patientReports"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("home/reportsFilter"));

        verify(districtService).getAllDistricts();

    }

}
