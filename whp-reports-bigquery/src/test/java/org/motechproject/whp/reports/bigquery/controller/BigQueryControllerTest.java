package org.motechproject.whp.reports.bigquery.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.whp.reports.bigquery.response.QueryResult;
import org.motechproject.whp.reports.bigquery.service.BigQueryService;

import java.util.ArrayList;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class BigQueryControllerTest {

    @Mock
    BigQueryService bigQueryService;

    BigQueryController bigQueryController;

    @Before
    public void setUp() {
        initMocks(this);
        bigQueryController = new BigQueryController(bigQueryService);
    }

    @Test
    public void shouldExecuteQuery() throws Exception {
        QueryResult queryResult = new QueryResult(new ArrayList<Map<String, Object>>());
        String expectedJson = new ObjectMapper().writer().writeValueAsString(queryResult);
        String query = "query";

        when(bigQueryService.executeQuery(query)).thenReturn(queryResult);

        standaloneSetup(bigQueryController).build()
                .perform(get("/bigquery/execute").param("queryName", query))
                .andExpect(content().string(expectedJson));
    }
}
