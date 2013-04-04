package org.motechproject.whp.reports.bigquery.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.motechproject.whp.reports.bigquery.model.FilterParams;
import org.motechproject.whp.reports.bigquery.response.QueryResult;
import org.motechproject.whp.reports.bigquery.service.BigQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequestMapping("/bigquery")
public class BigQueryController {

    BigQueryService bigQueryService;

    @Autowired
    public BigQueryController(BigQueryService bigQueryService) {
        this.bigQueryService = bigQueryService;
    }

    @RequestMapping("/execute")
    @ResponseBody
    public QueryResult executeQuery(@RequestParam String queryName, @RequestParam String filterParams) throws IOException {
          return bigQueryService.executeQuery(queryName, getFilterParams(filterParams));
    }

    private FilterParams getFilterParams(String filterParams) throws IOException {
        return new ObjectMapper().readValue(filterParams, FilterParams.class);
    }

}
