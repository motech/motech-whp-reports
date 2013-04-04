package org.motechproject.whp.reports.bigquery.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.whp.reports.bigquery.dao.BigQueryDAO;
import org.motechproject.whp.reports.bigquery.model.FilterParams;
import org.motechproject.whp.reports.bigquery.query.QueryBuilder;
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
    @Mock
    QueryBuilder queryBuilder;

    @Before
    public void setUp() {
        initMocks(this);
        bigQueryService = new BigQueryService(queries, bigQueryDAO, queryBuilder);
    }

    @Test
    public void shouldExecuteQueryForGivenQueryName() {
        String queryName = "queryName";
        String query = "query";
        String evaluatedQuery = "evaluatedQuery";

        FilterParams filterParams = new FilterParams();
        QueryResult expectedQueryResult = mock(QueryResult.class);

        when(queries.getQuery(queryName)).thenReturn(query);
        when(queryBuilder.build(query, filterParams)).thenReturn(evaluatedQuery);
        when(bigQueryDAO.executeQuery(evaluatedQuery, filterParams)).thenReturn(expectedQueryResult);

        assertEquals(expectedQueryResult, bigQueryService.executeQuery(queryName, filterParams));

    }
}
