package org.motechproject.whp.reports.dataservice;

import org.motechproject.whp.reports.domain.dimension.DateTimeDimension;
import org.motechproject.whp.reports.repository.DataAccessTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Arrays.asList;

@Service
public class DateTimeDimensionService {

    DataAccessTemplate template;
    PlatformTransactionManager manager;

    /*Required for spring proxy*/
    public DateTimeDimensionService() {

    }

    @Autowired
    public DateTimeDimensionService(DataAccessTemplate template, PlatformTransactionManager manager) {
        this.template = template;
        this.manager = manager;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<DateTimeDimension> save(final DateTimeDimension... dimensions) {
        for (final DateTimeDimension entity : dimensions) {
            template.saveOrUpdate(entity);
        }
        return asList(dimensions);
    }
}
