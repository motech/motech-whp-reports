package org.motechproject.whp.reports.export.query.dao.query.builder;

import org.apache.commons.lang3.StringUtils;
import org.motechproject.whp.reports.export.query.model.PatientReportRequest;

import java.util.ArrayList;
import java.util.List;


public class PatientReportsQueryBuilder {

    public static final String PATIENT_SUMMARY_SELECT_SQL = "select p.first_name, p.last_name, p.gender, p.patient_id, " +
            "treatment.tb_id, treatment.provider_id, treatment.provider_district, therapy.treatment_category, " +
            "treatment.start_date as tb_registration_date, therapy.start_date as treatment_start_date, therapy.disease_class, treatment.patient_type, " +
            "therapy.ip_pills_taken, therapy.ip_total_doses, therapy.eip_pills_taken, therapy.eip_total_doses, therapy.cp_pills_taken, " +
            "therapy.cp_total_doses, therapy.cumulative_missed_doses, therapy.creation_date, " +
            "treatment.treatment_outcome, treatment.end_date as treatment_closing_date, treatment.pretreatment_result as pre_treatment_sputum_result, " +
            "treatment.pretreatment_weight as pre_treatment_weight, therapy.patient_age as age, address.village, treatment.close_treatment_remarks, " +
            "treatment.district_with_code, treatment.tb_unit_with_code, treatment.ep_site, treatment.other_investigations, treatment.previous_treatment_history, " +
            "treatment.hiv_status, treatment.members_below_six_years, treatment.provider_type, treatment.cmf_doctor, treatment.xpert_test_result, "+
            "treatment.xpert_device_number, treatment.xpert_test_date, treatment.rif_resistance_result " +
            "from whp_reports.patient p " +
            "join whp_reports.patient_therapy therapy on p.patient_pk=therapy.patient_fk " +
            "join whp_reports.patient_treatment treatment on therapy.therapy_pk = treatment.therapy_fk " +
            "join whp_reports.patient_address address on p.patient_address_fk = address.address_pk " ;

    public static final String PATIENT_SUMMARY_SORT_SQL = " order by treatment_start_date ";
    public static final String LIMIT_ROWS = " limit 65000";
    public static final String WHERE_CLAUSE = "where";
    private static final String DEFAULT_TEST_DISTRICT_FILTER = " provider_district != 'TestDistrict'";

    private PatientReportRequest patientReportRequest;

    public PatientReportsQueryBuilder(PatientReportRequest patientReportRequest) {
        this.patientReportRequest = patientReportRequest;
    }

    public String build() {
        return PATIENT_SUMMARY_SELECT_SQL + buildPredicate() + PATIENT_SUMMARY_SORT_SQL + LIMIT_ROWS;
    }

    private String buildPredicate() {
        List<String> predicates = new ArrayList<>();

        predicates.add(DEFAULT_TEST_DISTRICT_FILTER);

        if(StringUtils.isNotEmpty(patientReportRequest.getDistrict())){
            predicates.add(String.format(" provider_district = '%s'", patientReportRequest.getDistrict()));
        }

        PredicateBuilder predicateBuilder = new PredicateBuilderFactory().getBuilder(patientReportRequest);
        if(predicateBuilder != null)
            predicates.addAll(predicateBuilder.getPredicates());

        return WHERE_CLAUSE + StringUtils.join(predicates, " AND");
    }
}
