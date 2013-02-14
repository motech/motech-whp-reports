package org.motechproject.whp.reports.export.query.dao;

import org.joda.time.LocalDate;
import org.junit.Test;
import org.motechproject.testing.utils.BaseUnitTest;
import org.motechproject.whp.reports.export.query.model.PatientReportRequest;

import static junit.framework.Assert.assertEquals;

public class PatientSummaryQueryBuilderTest extends BaseUnitTest {

    @Test
    public void shouldReturnQueryForPatientSummaryWithoutAnyUserGivenFilters() {
        LocalDate expectedEndDate = new LocalDate(2013, 01, 01);
        LocalDate expectedFromDate = expectedEndDate.minusDays(180);

        mockCurrentDate(expectedEndDate);

        String strExpectedEndDate = expectedEndDate.toString("yyyy-MM-dd");
        String strExpectedFromDate = expectedFromDate.toString("yyyy-MM-dd");

        PatientReportRequest emptyRequest = new PatientReportRequest();

        String expectedQuery = String.format(PatientSummaryQueryBuilder.PATIENT_SUMMARY_SELECT_SQL
                + "where treatment.start_date between '%s' AND '%s'"
                + PatientSummaryQueryBuilder.PATIENT_SUMMARY_SORT_SQL, strExpectedFromDate, strExpectedEndDate);

        assertEquals(expectedQuery, new PatientSummaryQueryBuilder(emptyRequest).build());
    }

    @Test
    public void shouldReturnQueryForPatientSummaryWithDistrict() {
        LocalDate expectedEndDate = new LocalDate(2013, 01, 01);
        LocalDate expectedFromDate = expectedEndDate.minusDays(180);

        mockCurrentDate(expectedEndDate);

        String strExpectedEndDate = expectedEndDate.toString("yyyy-MM-dd");
        String strExpectedFromDate = expectedFromDate.toString("yyyy-MM-dd");

        String expectedQuery = String.format(PatientSummaryQueryBuilder.PATIENT_SUMMARY_SELECT_SQL
                + "where provider_district = 'Begusarai' AND treatment.start_date between '%s' AND '%s'"
                + PatientSummaryQueryBuilder.PATIENT_SUMMARY_SORT_SQL, strExpectedFromDate, strExpectedEndDate);

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
        requestWithDates.setFrom("01/01/2013");
        requestWithDates.setTo("02/01/2013");
        assertEquals(expectedQuery, new PatientSummaryQueryBuilder(requestWithDates).build());
    }

    @Test
    public void shouldReturnQueryForPatientSummaryWithGivenDistrictAndBetweenTbRegistrationDates() {
        String expectedQuery = PatientSummaryQueryBuilder.PATIENT_SUMMARY_SELECT_SQL
                + "where provider_district = 'Begusarai' AND treatment.start_date between '2013-01-01' AND '2013-01-02'"
                + PatientSummaryQueryBuilder.PATIENT_SUMMARY_SORT_SQL;

        PatientReportRequest request = new PatientReportRequest();
        request.setDistrict("Begusarai");
        request.setFrom("01/01/2013");
        request.setTo("02/01/2013");

        assertEquals(expectedQuery, new PatientSummaryQueryBuilder(request)
                .build());

    }
}
