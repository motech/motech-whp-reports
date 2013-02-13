package org.motechproject.whp.reports.export.query.dao;

import org.motechproject.whp.reports.export.query.model.PatientSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class ReportQueryDAO {

    private JdbcTemplate jdbcTemplate;

    ReportQueryDAO() {
    }

    @Autowired
    public ReportQueryDAO(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<PatientSummary> getPatientSummaries() {
        return jdbcTemplate.query(new PatientSummaryQueryBuilder().build(),
                new BeanPropertyRowMapper(PatientSummary.class));
    }
}
