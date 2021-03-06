package org.motechproject.whp.reports.export.query.dao;

import org.junit.Test;
import org.motechproject.testing.utils.BaseUnitTest;
import org.motechproject.whp.reports.export.query.dao.query.builder.PatientReportsQueryBuilder;
import org.motechproject.whp.reports.export.query.model.PatientReportRequest;

import static junit.framework.Assert.assertEquals;

public class PatientReportsQueryBuilderTest extends BaseUnitTest {

    @Test
    public void shouldReturnQueryForPatientSummaryWithoutAnyUserGivenFilters() {
        PatientReportRequest emptyRequest = new PatientReportRequest();
        emptyRequest.setReportType(PatientReportType.SUMMARY_REPORT);

        String expectedQuery = String.format(PatientReportsQueryBuilder.PATIENT_SUMMARY_SELECT_SQL
                + "where provider_district != 'TestDistrict'"
                + PatientReportsQueryBuilder.PATIENT_SUMMARY_SORT_SQL
                + PatientReportsQueryBuilder.LIMIT_ROWS);

        assertEquals(expectedQuery, new PatientReportsQueryBuilder(emptyRequest).build());
    }

    @Test
    public void shouldReturnQueryForPatientSummaryWithDistrict() {
        String expectedQuery = String.format(PatientReportsQueryBuilder.PATIENT_SUMMARY_SELECT_SQL
                + "where provider_district != 'TestDistrict' AND provider_district = 'Begusarai'"
                + PatientReportsQueryBuilder.PATIENT_SUMMARY_SORT_SQL
                + PatientReportsQueryBuilder.LIMIT_ROWS);

        PatientReportRequest requestWithDistrict = new PatientReportRequest();
        requestWithDistrict.setDistrict("Begusarai");
        requestWithDistrict.setReportType(PatientReportType.SUMMARY_REPORT);
        assertEquals(expectedQuery, new PatientReportsQueryBuilder(requestWithDistrict).build());
    }

    @Test
    public void shouldReturnQueryForPatientSummaryBetweenGivenTbRegistrationDates() {
        String expectedQuery = PatientReportsQueryBuilder.PATIENT_SUMMARY_SELECT_SQL
                + "where provider_district != 'TestDistrict' AND treatment.start_date between '2013-01-01' AND '2013-01-02'"
                + PatientReportsQueryBuilder.PATIENT_SUMMARY_SORT_SQL
                + PatientReportsQueryBuilder.LIMIT_ROWS;

        PatientReportRequest requestWithDates = new PatientReportRequest();
        requestWithDates.setFrom("01/01/2013");
        requestWithDates.setTo("02/01/2013");
        requestWithDates.setReportType(PatientReportType.SUMMARY_REPORT);
        assertEquals(expectedQuery, new PatientReportsQueryBuilder(requestWithDates).build());
    }

    @Test
    public void shouldReturnQueryForPatientSummaryWithGivenDistrictAndBetweenTbRegistrationDates() {
        String expectedQuery = PatientReportsQueryBuilder.PATIENT_SUMMARY_SELECT_SQL
                + "where provider_district != 'TestDistrict' AND provider_district = 'Begusarai' AND treatment.start_date between '2013-01-01' AND '2013-01-02'"
                + PatientReportsQueryBuilder.PATIENT_SUMMARY_SORT_SQL
                + PatientReportsQueryBuilder.LIMIT_ROWS;

        PatientReportRequest request = new PatientReportRequest();
        request.setDistrict("Begusarai");
        request.setFrom("01/01/2013");
        request.setTo("02/01/2013");
        request.setReportType(PatientReportType.SUMMARY_REPORT);
        assertEquals(expectedQuery, new PatientReportsQueryBuilder(request)
                .build());

    }

    @Test
    public void shouldIgnorePredicatesWhenThereAreNoPredicateBuildersForGivenReportType() {
        String expectedQuery = PatientReportsQueryBuilder.PATIENT_SUMMARY_SELECT_SQL
                + "where provider_district != 'TestDistrict' AND provider_district = 'Begusarai'"
                + PatientReportsQueryBuilder.PATIENT_SUMMARY_SORT_SQL
                + PatientReportsQueryBuilder.LIMIT_ROWS;

        PatientReportRequest request = new PatientReportRequest();
        request.setDistrict("Begusarai");
        request.setFrom("01/01/2013");
        request.setTo("02/01/2013");
        request.setReportType(null);
        assertEquals(expectedQuery, new PatientReportsQueryBuilder(request)
                .build());

    }
}
