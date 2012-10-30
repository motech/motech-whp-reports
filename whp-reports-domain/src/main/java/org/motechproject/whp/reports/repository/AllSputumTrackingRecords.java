package org.motechproject.whp.reports.repository;


import org.motechproject.whp.reports.domain.measure.ContainerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AllSputumTrackingRecords {

    @Autowired
    private DataAccessTemplate template;

    public void save(ContainerRecord containerRecord) {
        template.saveOrUpdate(containerRecord);
    }

    public ContainerRecord get(Long sputumTrackingId) {
       return template.get(ContainerRecord.class, sputumTrackingId);
    }
}
