package org.motechproject.whp.reports.service;

import org.motechproject.whp.reports.domain.measure.SputumTracking;
import org.motechproject.whp.reports.repository.AllSputumTrackings;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SputumTrackingService {

    private AllSputumTrackings allSputumTrackings;

    /* Required for spring proxy */
    SputumTrackingService(){
    }

    public SputumTrackingService(AllSputumTrackings allSputumTrackings) {
        this.allSputumTrackings = allSputumTrackings;
    }

    public void save(SputumTracking sputumTracking) {
        allSputumTrackings.save(sputumTracking);
    }
}
