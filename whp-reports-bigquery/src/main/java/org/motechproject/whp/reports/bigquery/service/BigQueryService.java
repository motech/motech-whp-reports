package org.motechproject.whp.reports.bigquery.service;

import org.motechproject.whp.reports.bigquery.dao.BigQueryDAO;
import org.motechproject.whp.reports.bigquery.model.FilterParams;
import org.motechproject.whp.reports.bigquery.query.QueryBuilder;
import org.motechproject.whp.reports.bigquery.response.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BigQueryService {


    AllQueries allQueries;
    BigQueryDAO bigQueryDAO;
    private QueryBuilder queryBuilder;

    BigQueryService() {
    }

    @Autowired
    public BigQueryService(AllQueries allQueries, BigQueryDAO bigQueryDAO, QueryBuilder queryBuilder) {
        this.allQueries = allQueries;
        this.bigQueryDAO = bigQueryDAO;
        this.queryBuilder = queryBuilder;
    }

    public QueryResult executeQuery(String queryName, FilterParams filterParams) {
        String sqlTemplate = allQueries.getQuery(queryName);
        String sql = queryBuilder.build(sqlTemplate, filterParams);
        return bigQueryDAO.executeQuery(sql, filterParams);
    }
}
