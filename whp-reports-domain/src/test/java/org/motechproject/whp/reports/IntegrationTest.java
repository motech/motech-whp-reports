package org.motechproject.whp.reports;

import org.junit.After;
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
public abstract class IntegrationTest<T> {

    @Autowired
    protected DataAccessTemplate template;

    private List<Object> toDelete = new ArrayList<Object>();

    protected T purge(T entity) {
        toDelete.add(entity);
        return entity;
    }

    @After
    public void tearDown() {
        template.deleteAll(toDelete);
    }
}