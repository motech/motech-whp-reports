package org.motechproject.whp.reports.query;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.junit.Test;
import org.motechproject.bigquery.model.FilterParams;
import org.motechproject.bigquery.response.QueryResult;
import org.motechproject.bigquery.service.BigQueryService;
import org.motechproject.whp.reports.DBUnitTest;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ProviderPerformanceQueryScenariosIT extends DBUnitTest {

    private static final String DISTRICT1 = "district1";
    private static final String DISTRICT2 = "district2";

    @Autowired
    private BigQueryService queryService;

    @Test
    @DatabaseSetup(value = "providerPerformanceDataSetup.xml", type = DatabaseOperation.INSERT)
    @DatabaseTearDown(value = "providerPerformanceDataSetup.xml", type = DatabaseOperation.DELETE_ALL)
    public void shouldReturnProviderPerformanceByDistrict() throws Exception {

        QueryResult queryResult = queryService.executeQuery("provider.performance.by.district", new FilterParams());

        QueryResult expectedQueryResult = new QueryResultBuilder("district", "zero_week_bucket", "two_week_bucket", "three_to_five_week_bucket", "six_to_eight_week_bucket")
                .row(DISTRICT1, 1L, 2L, 0L, 0L)
                .row(DISTRICT2, 0L, 0L, 2L, 2L)
                .build();

        assertThat(queryResult.getContent().size(), is(2));
        assertThat(queryResult.getContent().get(0),is(expectedQueryResult.getContent().get(0)));
        assertThat(queryResult.getContent().get(1), is(expectedQueryResult.getContent().get(1)));

    }

}

