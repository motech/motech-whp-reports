package org.motechproject.whp.reports.bigquery.service;

import org.motechproject.whp.reports.bigquery.dao.QueryDAO;
import org.motechproject.whp.reports.bigquery.response.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueryService {

    Queries queries;
    QueryDAO queryDAO;

    QueryService() {
    }

    @Autowired
    public QueryService(Queries queries, QueryDAO queryDAO) {
        this.queries = queries;
        this.queryDAO = queryDAO;
    }

    public QueryResult executeQuery(String queryName) {
        String query = queries.getQuery(queryName);
        return queryDAO.executeQuery(query);
    }
}
