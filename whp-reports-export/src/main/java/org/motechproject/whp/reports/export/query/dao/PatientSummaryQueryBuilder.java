package org.motechproject.whp.reports.export.query.dao;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatientSummaryQueryBuilder {


    public static final String PATIENT_SUMMARY_SELECT_SQL = "select p.first_name, p.last_name, p.gender, p.patient_id, " +
            "treatment.tb_id, treatment.provider_id, treatment.provider_district, therapy.treatment_category, " +
            "treatment.start_date as tb_registration_date, therapy.start_date as treatment_start_date, therapy.disease_class, treatment.patient_type, " +
            "therapy.ip_pills_taken, therapy.ip_total_doses, therapy.cp_pills_taken, " +
            "therapy.cp_total_doses, therapy.cumulative_missed_doses, " +
            "treatment.treatment_outcome, treatment.end_date as treatment_closing_date, treatment.pretreatment_result as pre_treatment_sputum_result, " +
            "treatment.pretreatment_weight as pre_treatment_weight, therapy.patient_age as age, address.village from whp_reports.patient p " +
            "join whp_reports.patient_therapy therapy on p.patient_pk=therapy.patient_fk " +
            "join whp_reports.patient_treatment treatment on therapy.therapy_pk = treatment.therapy_fk " +
            "join whp_reports.patient_address address on p.patient_address_fk = address.address_pk " ;

    public static final String PATIENT_SUMMARY_SORT_SQL = " order by treatment_start_date";
    public static final String DISTRICT = "district";
    public static final String WHERE_CLAUSE = "where";
    public static final String TB_REGISTRATION_DATE_FROM = "tbRegistrationDateFrom";
    public static final String TB_REGISTRATION_DATE_TO = "tbRegistrationDateTo";

    private Map<String, String> params = new HashMap<>();

    public String build() {
        return PATIENT_SUMMARY_SELECT_SQL + buildPredicate() + PATIENT_SUMMARY_SORT_SQL;
    }

    private String buildPredicate() {
        if(params.isEmpty()) {
            return "";
        }

        List<String> predicates = new ArrayList<>();

        if(params.containsKey(DISTRICT)){
            predicates.add(String.format(" provider_district = '%s'", params.get(DISTRICT)));
        }

        if(params.containsKey(TB_REGISTRATION_DATE_FROM)){
            predicates.add(String.format(" tb_registration_date between '%s' AND '%s'",
                    params.get(TB_REGISTRATION_DATE_FROM),
                    params.get(TB_REGISTRATION_DATE_TO)));
        }

        return  WHERE_CLAUSE + StringUtils.join(predicates, " AND");
    }

    public PatientSummaryQueryBuilder withDistrict(String district) {
        params.put("district", district);
        return this;
    }

    public PatientSummaryQueryBuilder withTBRegistrationDates(String tbRegistrationDateFrom, String tbRegistrationDateTo) {
        params.put(TB_REGISTRATION_DATE_FROM, tbRegistrationDateFrom);
        params.put(TB_REGISTRATION_DATE_TO, tbRegistrationDateTo);
        return this;
    }
}
