package org.motechproject.whp.reports.bigquery.controller;

import org.motechproject.whp.reports.bigquery.response.QueryResult;
import org.motechproject.whp.reports.bigquery.service.WhpQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/bigquery")
public class QueryController {

    WhpQueryService whpQueryService;

    @Autowired
    public QueryController(WhpQueryService whpQueryService) {
        this.whpQueryService = whpQueryService;
    }

    @RequestMapping("/execute")
    @ResponseBody
    public QueryResult executeQuery(@RequestParam String queryName){
          return whpQueryService.executeQuery(queryName);
    }

}
