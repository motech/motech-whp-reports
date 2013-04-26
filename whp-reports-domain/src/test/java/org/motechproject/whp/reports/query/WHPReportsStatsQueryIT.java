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

public class WHPReportsStatsQueryIT extends DBUnitTest{

    @Autowired
    private BigQueryService queryService;

    @Test
    @DatabaseSetup(value = "patients.xml", type = DatabaseOperation.INSERT)
    @DatabaseTearDown(value = "patients.xml", type = DatabaseOperation.DELETE_ALL)
    public void shouldReturnProviderPerformanceByDistrict() throws Exception {

        QueryResult queryResult = queryService.executeQuery("whp.reports.stats", new FilterParams());

        QueryResult expectedQueryResult = new QueryResultBuilder("count", "subject")
                                            .row(1L,"active_providers")
                                            .row(4L,"all_patients")
                                            .row(1L,"active_rhp_providers")
                                            .row(1L,"active_tpc_providers")
                                            .row(2L,"active_patients")
                                            .build();

        assertThat(queryResult.getContent().size(), is(5));
        assertThat(queryResult.getContent().get(0),is(expectedQueryResult.getContent().get(0)));
        assertThat(queryResult.getContent().get(1),is(expectedQueryResult.getContent().get(1)));
        assertThat(queryResult.getContent().get(2),is(expectedQueryResult.getContent().get(2)));
        assertThat(queryResult.getContent().get(3),is(expectedQueryResult.getContent().get(3)));
        assertThat(queryResult.getContent().get(4),is(expectedQueryResult.getContent().get(4)));
    }
}
