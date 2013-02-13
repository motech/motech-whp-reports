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

    public static final String PATIENT_SUMMARY_SQL = "select p.first_name, p.last_name, p.gender, p.patient_id, " +
            "treatment.tb_id, treatment.provider_id, treatment.provider_district, therapy.treatment_category, " +
            "treatment.start_date as tb_registration_date, therapy.start_date as treatment_start_date, therapy.disease_class, treatment.patient_type, " +
            "therapy.ip_pills_taken, therapy.ip_total_doses, therapy.cp_pills_taken, " +
            "therapy.cp_total_doses, therapy.cumulative_missed_doses, " +
            "treatment.treatment_outcome, treatment.end_date as treatment_closing_date, treatment.pretreatment_result as pre_treatment_sputum_result, " +
            "treatment.pretreatment_weight as pre_treatment_weight, therapy.patient_age as age, address.village from whp_reports.patient p " +
            "join whp_reports.patient_therapy therapy on p.patient_pk=therapy.patient_fk " +
            "join whp_reports.patient_treatment treatment on therapy.therapy_pk = treatment.therapy_fk " +
            "join whp_reports.patient_address address on p.patient_address_fk = address.address_pk order by treatment_start_date";

    public List<PatientSummary> getPatientSummaries() {
        return jdbcTemplate.query(PATIENT_SUMMARY_SQL,
                new BeanPropertyRowMapper(PatientSummary.class));
    }
}
