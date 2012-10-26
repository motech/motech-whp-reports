package org.motechproject.whp.reports.repository;


import org.motechproject.whp.reports.domain.measure.SputumTracking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AllSputumTrackings{

    @Autowired
    private DataAccessTemplate template;

    public void save(SputumTracking sputumTracking) {
        template.saveOrUpdate(sputumTracking);
    }

    public SputumTracking get(Long sputumTrackingId) {
       return template.get(SputumTracking.class, sputumTrackingId);
    }
}
