package org.motechproject.whp.reports.export.query.dao;

import org.junit.Test;
import org.motechproject.whp.reports.export.query.model.PatientReportRequest;

import static junit.framework.Assert.assertEquals;

public class PatientSummaryQueryBuilderTest {

    @Test
    public void shouldReturnQueryForPatientSummaryWithoutAnyUserGivenFilters() {
        PatientReportRequest emptyRequest = new PatientReportRequest();
        assertEquals(PatientSummaryQueryBuilder.PATIENT_SUMMARY_SELECT_SQL + PatientSummaryQueryBuilder.PATIENT_SUMMARY_SORT_SQL, new PatientSummaryQueryBuilder(emptyRequest).build());
    }

    @Test
    public void shouldReturnQueryForPatientSummaryWithDistrict() {
        String expectedQuery = PatientSummaryQueryBuilder.PATIENT_SUMMARY_SELECT_SQL
                + "where provider_district = 'Begusarai'"
                + PatientSummaryQueryBuilder.PATIENT_SUMMARY_SORT_SQL;

        PatientReportRequest requestWithDistrict = new PatientReportRequest();
        requestWithDistrict.setDistrict("Begusarai");
        assertEquals(expectedQuery, new PatientSummaryQueryBuilder(requestWithDistrict).build());
    }

    @Test
    public void shouldReturnQueryForPatientSummaryBetweenGivenTbRegistrationDates() {
        String expectedQuery = PatientSummaryQueryBuilder.PATIENT_SUMMARY_SELECT_SQL
                + "where treatment.start_date between '2013-01-01' AND '2013-01-02'"
                + PatientSummaryQueryBuilder.PATIENT_SUMMARY_SORT_SQL;

        PatientReportRequest requestWithDates = new PatientReportRequest();
        requestWithDates.setTbRegistrationDateFrom("2013-01-01");
        requestWithDates.setTbRegistrationDateTo("2013-01-02");
        assertEquals(expectedQuery, new PatientSummaryQueryBuilder(requestWithDates).build());
    }

    @Test
    public void shouldReturnQueryForPatientSummaryWithGivenDistrictAndBetweenTbRegistrationDates() {
        String expectedQuery = PatientSummaryQueryBuilder.PATIENT_SUMMARY_SELECT_SQL
                + "where provider_district = 'Begusarai' AND treatment.start_date between '2013-01-01' AND '2013-01-02'"
                + PatientSummaryQueryBuilder.PATIENT_SUMMARY_SORT_SQL;

        PatientReportRequest request = new PatientReportRequest();
        request.setDistrict("Begusarai");
        request.setTbRegistrationDateFrom("2013-01-01");
        request.setTbRegistrationDateTo("2013-01-02");

        assertEquals(expectedQuery, new PatientSummaryQueryBuilder(request)
                .build());

    }
}
