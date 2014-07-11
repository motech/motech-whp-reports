package org.motechproject.whp.reports.export.query.dao;

import java.util.List;

import javax.sql.DataSource;

import org.motechproject.whp.reports.config.WhpReportQueries;
import org.motechproject.whp.reports.export.query.dao.query.builder.PatientReportsQueryBuilder;
import org.motechproject.whp.reports.export.query.dao.query.builder.ProviderReportsQueryBuilder;
import org.motechproject.whp.reports.export.query.model.AdherenceAuditLogSummary;
import org.motechproject.whp.reports.export.query.model.AdherenceRecordSummary;
import org.motechproject.whp.reports.export.query.model.PatientReminderCallLogSummary;
import org.motechproject.whp.reports.export.query.model.PatientReportRequest;
import org.motechproject.whp.reports.export.query.model.PatientSummary;
import org.motechproject.whp.reports.export.query.model.ProviderReminderCallLogSummary;
import org.motechproject.whp.reports.export.query.model.ProviderReportRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ReportQueryDAO {

    private JdbcTemplate jdbcTemplate;

    WhpReportQueries whpReportQueries;

    ReportQueryDAO() {
    }

    @Autowired
    public ReportQueryDAO(DataSource dataSource, WhpReportQueries whpReportQueries) {
        this.whpReportQueries = whpReportQueries;
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<PatientSummary> getPatientSummaries(PatientReportRequest patientReportRequest) {
        return jdbcTemplate.query(new PatientReportsQueryBuilder(patientReportRequest).build(),
                new BeanPropertyRowMapper(PatientSummary.class));
    }

    public List<AdherenceAuditLogSummary> getAdherenceAuditLogSummaries() {
        return jdbcTemplate.query(whpReportQueries.getAdherenceAuditLogReportQuery(), new BeanPropertyRowMapper(AdherenceAuditLogSummary.class));
    }

    public List<AdherenceRecordSummary> getAdherenceRecordSummaries() {
        return jdbcTemplate.query(whpReportQueries.getAdherenceDataReportQuery(), new BeanPropertyRowMapper(AdherenceRecordSummary.class));
    }

    public List<ProviderReminderCallLogSummary> getProviderReminderCallLogSummaries(ProviderReportRequest providerReportRequest) {
        return jdbcTemplate.query(new ProviderReportsQueryBuilder(providerReportRequest).build(), new BeanPropertyRowMapper(ProviderReminderCallLogSummary.class));
    }
    
    public List<PatientReminderCallLogSummary> getPatientReminderCallLogSummaries(PatientReportRequest patientReportRequest) {
    	return jdbcTemplate.query(new PatientReportsQueryBuilder(patientReportRequest).build(),
                new BeanPropertyRowMapper(PatientReminderCallLogSummary.class));    }
}
