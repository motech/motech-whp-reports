package org.motechproject.whp.reports.bigquery.dao;


import org.motechproject.whp.reports.bigquery.model.FilterParams;
import org.motechproject.whp.reports.bigquery.response.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Repository
public class BigQueryDAO {

    private NamedParameterJdbcTemplate jdbcTemplate;
    private SqlQueryParamBuilder sqlQueryParamBuilder;

    BigQueryDAO() {
    }

    @Autowired
    public BigQueryDAO(DataSource dataSource, SqlQueryParamBuilder sqlQueryParamBuilder) {
        this.sqlQueryParamBuilder = sqlQueryParamBuilder;
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public QueryResult executeQuery(String query, FilterParams filterParams){
        Map<String, Object> queryParams = sqlQueryParamBuilder.buildQueryParams(filterParams);
        List<Map<String,Object>> list = jdbcTemplate.queryForList(query, queryParams);
        return new QueryResult(list);
    }
}
