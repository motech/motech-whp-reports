package org.motechproject.whp.reports.bigquery.service;

import org.motechproject.whp.reports.bigquery.dao.WhpQueryDAO;
import org.motechproject.whp.reports.bigquery.response.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WhpQueryService {

    Queries queries;
    WhpQueryDAO whpQueryDAO;

    WhpQueryService() {
    }

    @Autowired
    public WhpQueryService(Queries queries, WhpQueryDAO whpQueryDAO) {
        this.queries = queries;
        this.whpQueryDAO = whpQueryDAO;
    }

    public QueryResult executeQuery(String queryName) {
        String query = queries.getQuery(queryName);
        return whpQueryDAO.executeQuery(query);
    }
}
