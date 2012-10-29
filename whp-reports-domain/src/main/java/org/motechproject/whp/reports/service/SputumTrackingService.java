package org.motechproject.whp.reports.service;

import org.motechproject.whp.reports.domain.measure.SputumTrackingRecord;
import org.motechproject.whp.reports.repository.AllSputumTrackingRecords;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SputumTrackingService {

    private AllSputumTrackingRecords allSputumTrackingRecords;

    /* Required for spring proxy */
    SputumTrackingService(){
    }

    public SputumTrackingService(AllSputumTrackingRecords allSputumTrackingRecords) {
        this.allSputumTrackingRecords = allSputumTrackingRecords;
    }

    public void save(SputumTrackingRecord sputumTrackingRecord) {
        allSputumTrackingRecords.save(sputumTrackingRecord);
    }
}
