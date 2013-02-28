package org.motechproject.whp.reports.export.query.dao;

import org.motechproject.whp.reports.config.ReportQueries;
import org.motechproject.whp.reports.export.query.dao.query.builder.PatientReportsQueryBuilder;
import org.motechproject.whp.reports.export.query.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class ReportQueryDAO {

    private JdbcTemplate jdbcTemplate;

    ReportQueries reportQueries;

    ReportQueryDAO() {
    }

    @Autowired
    public ReportQueryDAO(DataSource dataSource, ReportQueries reportQueries) {
        this.reportQueries = reportQueries;
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<PatientSummary> getPatientSummaries(PatientReportRequest patientReportRequest) {
        return jdbcTemplate.query(new PatientReportsQueryBuilder(patientReportRequest).build(),
                new BeanPropertyRowMapper(PatientSummary.class));
    }

    public List<AdherenceAuditLogSummary> getAdherenceAuditLogSummaries() {
        return jdbcTemplate.query(reportQueries.getAdherenceAuditLogReportQuery(), new BeanPropertyRowMapper(AdherenceAuditLogSummary.class));
    }

    public List<AdherenceRecordSummary> getAdherenceRecordSummaries() {
        return jdbcTemplate.query(reportQueries.getAdherenceDataReportQuery(), new BeanPropertyRowMapper(AdherenceRecordSummary.class));
    }

    public List<ProviderReminderCallLogSummary> getProviderReminderCallLogSummaries() {
        return jdbcTemplate.query(reportQueries.getProviderReminderCallLogQuery(), new BeanPropertyRowMapper(ProviderReminderCallLogSummary.class));
    }

    public List<ContainerSummary> getContainerSummaries() {
        return jdbcTemplate.query(reportQueries.getContainerRecordQuery(), new BeanPropertyRowMapper(ContainerSummary.class));
    }
}
