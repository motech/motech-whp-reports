package org.motechproject.whp.reports.repository;


import org.motechproject.whp.reports.domain.measure.SputumTrackingRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AllSputumTrackingRecords {

    @Autowired
    private DataAccessTemplate template;

    public void save(SputumTrackingRecord sputumTrackingRecord) {
        template.saveOrUpdate(sputumTrackingRecord);
    }

    public SputumTrackingRecord get(Long sputumTrackingId) {
       return template.get(SputumTrackingRecord.class, sputumTrackingId);
    }
}
