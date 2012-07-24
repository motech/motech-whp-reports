package org.motechproject.whp.reports;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.motechproject.whp.reports.repository.DataAccessTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationReportingDomainContext.xml")
@Transactional
public abstract class SpringIntegrationTest {

    @Autowired
    protected DataAccessTemplate template;

    private List<Object> toDelete = new ArrayList<Object>();

    protected void markForDeletion(Object entity) {
        toDelete.add(entity);
    }

    protected void tearDown() {
        template.deleteAll(toDelete);
    }
}