package org.motechproject.whp.reports.webservice.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.whp.reports.contract.SputumTrackingRequest;
import org.motechproject.whp.reports.service.SputumTrackingService;

import static org.mockito.MockitoAnnotations.initMocks;

public class SputumTrackingControllerTest {

    @Mock
    private SputumTrackingService sputumTrackingService;
    private SputumTrackingController sputumTrackingController;

    @Before
    public void setUp(){
        initMocks(this);
        sputumTrackingController = new SputumTrackingController(sputumTrackingService);
    }

    @Test
    public void shouldRegisterContainer() {

        SputumTrackingRequest sputumTrackingRequest = new SputumTrackingRequest();
    }
}
