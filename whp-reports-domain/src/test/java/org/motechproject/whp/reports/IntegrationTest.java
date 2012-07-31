package org.motechproject.whp.reports;

import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.junit.After;
import org.junit.runner.RunWith;
import org.motechproject.whp.reports.repository.DataAccessTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationReportingDomainContext.xml")
public abstract class IntegrationTest<T> {

    @Autowired
    protected DataAccessTemplate template;

    @Autowired
    protected PlatformTransactionManager manager;

    private List<Object> toDelete = new ArrayList<Object>();

    public T purge(T entity) {
        toDelete.add(entity);
        return entity;
    }

    public List<T> purgeAll(List<T> entities) {
        for (T entity : entities) {
            purge(entity);
        }
        return entities;
    }

    public List<T> purgeAll(T... entities) {
        for (T entity : entities) {
            purge(entity);
        }
        return asList(entities);
    }

    protected List<T> saveOrUpdate(T... entities) {
        for (final T entity : entities) {
            template.saveOrUpdate(entity);
            purge(entity);
        }
        return asList(entities);
    }

    protected List<T> fetch(final String queryString) {
        final List<T> result = new ArrayList<>();
        Session session = template.getSessionFactory().getCurrentSession();
        Query query = session.createQuery(queryString);
        List entities = query.list();
        result.addAll(entities);
        return result;
    }
}