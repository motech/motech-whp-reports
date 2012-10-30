package org.motechproject.whp.reports.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.whp.reports.domain.measure.ContainerRecord;
import org.motechproject.whp.reports.repository.AllSputumTrackingRecords;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class SputumTrackingServiceTest {

    @Mock
    private AllSputumTrackingRecords allSputumTrackingRecords;
    private SputumTrackingService sputumTrackingService;

    @Before()
    public void setup(){
        initMocks(this);
        sputumTrackingService = new SputumTrackingService(allSputumTrackingRecords);
    }

    @Test
    public void shouldSaveSputumTrackingRecord(){

        ContainerRecord containerRecord = new ContainerRecord();
        sputumTrackingService.save(containerRecord);

        verify(allSputumTrackingRecords).save(containerRecord);
    }

}
