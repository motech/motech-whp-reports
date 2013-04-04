package org.motechproject.whp.reports.bigquery.service;

import org.motechproject.whp.reports.bigquery.dao.BigQueryDAO;
import org.motechproject.whp.reports.bigquery.model.FilterParams;
import org.motechproject.whp.reports.bigquery.query.QueryBuilder;
import org.motechproject.whp.reports.bigquery.response.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BigQueryService {

    Queries queries;
    BigQueryDAO bigQueryDAO;
    private QueryBuilder queryBuilder;

    BigQueryService() {
    }

    @Autowired
    public BigQueryService(Queries queries, BigQueryDAO bigQueryDAO, QueryBuilder queryBuilder) {
        this.queries = queries;
        this.bigQueryDAO = bigQueryDAO;
        this.queryBuilder = queryBuilder;
    }

    public QueryResult executeQuery(String queryName, FilterParams filterParams) {
        String sqlTemplate = queries.getQuery(queryName);
        String sql = queryBuilder.build(sqlTemplate, filterParams);
        return bigQueryDAO.executeQuery(sql, filterParams);
    }
}
