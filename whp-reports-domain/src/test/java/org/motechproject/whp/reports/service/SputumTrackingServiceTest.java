package org.motechproject.whp.reports.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.whp.reports.domain.measure.SputumTracking;
import org.motechproject.whp.reports.repository.AllSputumTrackings;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class SputumTrackingServiceTest {

    @Mock
    private AllSputumTrackings allSputumTrackings;
    private SputumTrackingService sputumTrackingService;

    @Before()
    public void setup(){
        initMocks(this);
        sputumTrackingService = new SputumTrackingService(allSputumTrackings);
    }

    @Test
    public void shouldSaveSputumTrackingRecord(){

        SputumTracking sputumTracking = new SputumTracking();
        sputumTrackingService.save(sputumTracking);

        verify(allSputumTrackings).save(sputumTracking);
    }

}
