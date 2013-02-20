package org.motechproject.whp.reports.export.query.dao;

import org.motechproject.whp.reports.export.query.model.AdherenceAuditLogSummary;
import org.motechproject.whp.reports.export.query.model.PatientReportRequest;
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

    public List<PatientSummary> getPatientSummaries(PatientReportRequest patientReportRequest) {
        return jdbcTemplate.query(new PatientReportsQueryBuilder(patientReportRequest).build(),
                new BeanPropertyRowMapper(PatientSummary.class));
    }

    public List<AdherenceAuditLogSummary> getAdherenceAuditLogSummaries() {
        return jdbcTemplate.query(new AdherenceAuditLogReportQueryBuilder().build(), new BeanPropertyRowMapper(AdherenceAuditLogSummary.class));
    }
}
