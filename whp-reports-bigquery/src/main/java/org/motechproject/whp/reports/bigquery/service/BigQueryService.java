package org.motechproject.whp.reports.bigquery.service;

import org.motechproject.whp.reports.bigquery.dao.BigQueryDAO;
import org.motechproject.whp.reports.bigquery.model.FilterParams;
import org.motechproject.whp.reports.bigquery.response.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BigQueryService {

    Queries queries;
    BigQueryDAO bigQueryDAO;

    BigQueryService() {
    }

    @Autowired
    public BigQueryService(Queries queries, BigQueryDAO bigQueryDAO) {
        this.queries = queries;
        this.bigQueryDAO = bigQueryDAO;
    }

    public QueryResult executeQuery(String queryName, FilterParams filterParams) {
        String query = queries.getQuery(queryName);
        return bigQueryDAO.executeQuery(query, filterParams);
    }
}
