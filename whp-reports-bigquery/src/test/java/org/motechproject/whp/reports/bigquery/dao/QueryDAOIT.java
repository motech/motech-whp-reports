package org.motechproject.whp.reports.bigquery.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.motechproject.whp.reports.bigquery.response.QueryResult;
import org.motechproject.whp.reports.bigquery.service.Queries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collection;

import static junit.framework.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-applicationReportingBigQueryContext.xml")
public class QueryDAOIT {

    @Autowired
    BigQueryDAO bigQueryDAO;

    @Autowired
    Queries queries;

    @Test
    public void shouldExecuteQuery() {
        Collection<String> allQueries = queries.all();

        for(String query : allQueries){
            QueryResult queryResult = bigQueryDAO.executeQuery(query);
            assertNotNull(queryResult);
        }
    }
}
