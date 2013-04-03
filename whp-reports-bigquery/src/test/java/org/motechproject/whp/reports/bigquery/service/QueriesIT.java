package org.motechproject.whp.reports.bigquery.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-applicationReportingBigQueryContext.xml")
public class QueriesIT {

    @Autowired
    Queries queries;

    @Test
    public void shouldGetQuery() {
        assertThat(queries.all().size(), greaterThan(0));
        assertNotNull(queries.getQuery("flashing.calls.per.day"));
    }

}
