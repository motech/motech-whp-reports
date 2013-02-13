package org.motechproject.whp.reports.export.query.dao;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class PatientSummaryQueryBuilderTest {

    @Test
    public void shouldReturnQueryForPatientSummaryWithoutAnyUserGivenFilters() {
        assertEquals(PatientSummaryQueryBuilder.PATIENT_SUMMARY_SELECT_SQL + PatientSummaryQueryBuilder.PATIENT_SUMMARY_SORT_SQL, new PatientSummaryQueryBuilder().build());
    }

    @Test
    public void shouldReturnQueryForPatientSummaryWithDistrict() {
        String expectedQuery = PatientSummaryQueryBuilder.PATIENT_SUMMARY_SELECT_SQL
                + "where provider_district = 'Begusarai'"
                + PatientSummaryQueryBuilder.PATIENT_SUMMARY_SORT_SQL;

        assertEquals(expectedQuery, new PatientSummaryQueryBuilder().withDistrict("Begusarai").build());
    }

    @Test
    public void shouldReturnQueryForPatientSummaryBetweenGivenTbRegistrationDates() {
        String expectedQuery = PatientSummaryQueryBuilder.PATIENT_SUMMARY_SELECT_SQL
                + "where tb_registration_date between '2013-01-01' AND '2013-01-02'"
                + PatientSummaryQueryBuilder.PATIENT_SUMMARY_SORT_SQL;

        assertEquals(expectedQuery, new PatientSummaryQueryBuilder().withTBRegistrationDates("2013-01-01", "2013-01-02").build());
    }

    @Test
    public void shouldReturnQueryForPatientSummaryWithGivenDistrictAndBetweenTbRegistrationDates() {
        String expectedQuery = PatientSummaryQueryBuilder.PATIENT_SUMMARY_SELECT_SQL
                + "where provider_district = 'Begusarai' AND tb_registration_date between '2013-01-01' AND '2013-01-02'"
                + PatientSummaryQueryBuilder.PATIENT_SUMMARY_SORT_SQL;

        assertEquals(expectedQuery, new PatientSummaryQueryBuilder()
                .withDistrict("Begusarai")
                .withTBRegistrationDates("2013-01-01", "2013-01-02").build());

    }
}
