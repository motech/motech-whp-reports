package org.motechproject.whp.reports.dao;

import org.motechproject.whp.reports.contract.query.PatientAdherenceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class PatientQueryDAO {

    private JdbcTemplate jdbcTemplate;

    PatientQueryDAO() {
    }

    @Autowired
    public PatientQueryDAO(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private static final String ACTIVE_PATIENTS_WITH_MISSING_ADHERENCE = "select patient_id, phone_no as mobile_number, " +
            "adherence_missing as missing_weeks from whp_reports.patient inner join whp_reports.patient_alert " +
            "on patient_alert_fk = alert_pk " +
            "where adherence_missing > 0 and phone_no is not null and is_active = 'Y' limit ? offset ?";
    public List<PatientAdherenceInfo> findPatientDetailsOfActivePatientsWithAdherenceMissing(int skip, int limit) {
        return jdbcTemplate.query(ACTIVE_PATIENTS_WITH_MISSING_ADHERENCE,
                new BeanPropertyRowMapper(PatientAdherenceInfo.class), limit, skip);
    }
}
