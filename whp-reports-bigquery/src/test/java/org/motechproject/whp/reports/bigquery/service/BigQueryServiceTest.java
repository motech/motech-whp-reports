package org.motechproject.whp.reports.bigquery.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.whp.reports.bigquery.dao.BigQueryDAO;
import org.motechproject.whp.reports.bigquery.model.FilterParams;
import org.motechproject.whp.reports.bigquery.response.QueryResult;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class BigQueryServiceTest {

    BigQueryService bigQueryService;

    @Mock
    BigQueryDAO bigQueryDAO;
    @Mock
    Queries queries;

    @Before
    public void setUp() {
        initMocks(this);
        bigQueryService = new BigQueryService(queries, bigQueryDAO);
    }

    @Test
    public void shouldExecuteQueryForGivenQueryName() {
        String queryName = "queryName";
        String query = "query";
        FilterParams filterParams = new FilterParams();
        QueryResult expectedQueryResult = mock(QueryResult.class);

        when(queries.getQuery(queryName)).thenReturn(query);
        when(bigQueryDAO.executeQuery(query, filterParams)).thenReturn(expectedQueryResult);

        assertEquals(expectedQueryResult, bigQueryService.executeQuery(queryName, filterParams));

    }

}
