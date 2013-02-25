package org.motechproject.whp.reports.dao;

import org.joda.time.LocalDate;
import org.motechproject.whp.reports.contract.adherence.ProviderAdherenceSummary;
import org.motechproject.whp.reports.date.WHPDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Date;
import java.util.List;

import static org.motechproject.util.DateUtil.today;
import static org.motechproject.whp.reports.domain.TreatmentWeekInstance.week;

@Repository
public class ProviderAdherenceQueryDAO {
    private JdbcTemplate jdbcTemplate;

    ProviderAdherenceQueryDAO() {
    }

    @Autowired
    public ProviderAdherenceQueryDAO(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private static final String PROVIDER_CURRENT_ADHERENCE_SUMMARY_QUERY = "select provider.provider_id, " +
            "provider.primary_mobile, provider.secondary_mobile,provider.tertiary_mobile, " +
            "case when exists " +
            "(select 1 from whp_reports.adherence_record where provider.provider_id = adherence_record.provider_id " +
            "and pill_date between ? and ?) then '1' else '0' end as adherence_given " +
            "from whp_reports.provider where exists (select 1 from  whp_reports.patient_therapy therapy " +
            "inner join whp_reports.patient_treatment treatment on treatment.therapy_fk = therapy.therapy_pk " +
            "where treatment.provider_id = provider.provider_id " +
            "and treatment.is_current_treatment = 'Y' " +
            "and therapy.status != 'Closed' " +
            "and therapy.start_date <= ? " +
            "and treatment.end_date is  null) " +
            "and provider.district = ?";

    public List<ProviderAdherenceSummary> getProviderAdherenceSummaries(String district) {
        LocalDate today = today();
        LocalDate treatmentWeekStartDate = week(today).startDate();
        LocalDate treatmentWeekEndDate = week(today).endDate();

        Date startDate = WHPDate.toSqlDate(treatmentWeekStartDate);
        Date endDate = WHPDate.toSqlDate(treatmentWeekEndDate);

        return jdbcTemplate.query(PROVIDER_CURRENT_ADHERENCE_SUMMARY_QUERY,
                new BeanPropertyRowMapper(ProviderAdherenceSummary.class), startDate, endDate, startDate, district);
    }

}