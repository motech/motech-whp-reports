package org.motechproject.whp.reports.bigquery.dao;


import org.motechproject.whp.reports.bigquery.response.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Repository
public class BigQueryDAO {

    private JdbcTemplate jdbcTemplate;

    BigQueryDAO() {
    }

    @Autowired
    public BigQueryDAO(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public QueryResult executeQuery(String query){
        List<Map<String,Object>> list = jdbcTemplate.queryForList(query);
        return new QueryResult(list);
    }
}
