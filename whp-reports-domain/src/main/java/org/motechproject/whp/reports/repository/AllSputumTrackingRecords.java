package org.motechproject.whp.reports.repository;


import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.motechproject.whp.reports.domain.measure.ContainerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    public ContainerRecord getByContainerId(String containerId) {
        Criteria criteria = template.getSessionFactory().getCurrentSession().createCriteria(ContainerRecord.class);
        criteria.add(Restrictions.eq("containerId", containerId));
        List containerRecords = criteria.list();
        return containerRecords != null && !containerRecords.isEmpty() ? (ContainerRecord) containerRecords.get(0) : null;
    }
}
