package org.motechproject.whp.reports.export.query.model;

import org.joda.time.LocalDate;
import org.junit.Test;
import org.motechproject.testing.utils.BaseUnitTest;

import static junit.framework.Assert.assertEquals;

public class DateRangeTest extends BaseUnitTest{


    @Test
    public void shouldReturnStartAndEndDatesInSqlDateFormat() {
        DateRange dateRange = new DateRange("12/12/2012", "25/12/2012");

        assertEquals("2012-12-12", dateRange.getStartDateInSqlFormat());
        assertEquals("2012-12-25", dateRange.getEndDateInSqlFormat());
    }

    @Test
    public void shouldReturnStartAndEndDatesInDefaultDateFormat() {
        DateRange dateRange = new DateRange("12/12/2012", "25/12/2012");

        assertEquals("12/12/2012", dateRange.getStartDate());
        assertEquals("25/12/2012", dateRange.getEndDate());
    }

    @Test
    public void shouldReturnDefaultStartAndEndDates() {
        LocalDate today = new LocalDate(2012, 12, 12);
        mockCurrentDate(today);
        DateRange dateRange = new DateRange(null, null);

        assertEquals(today.minusDays(180).toString("yyyy-MM-dd"), dateRange.getStartDateInSqlFormat());
        assertEquals(today.toString("yyyy-MM-dd"), dateRange.getEndDateInSqlFormat());
    }

    @Test
    public void shouldReturnDefaultStartAndEndDatesForEmptyDateValues() {
        LocalDate today = new LocalDate(2012, 12, 12);
        mockCurrentDate(today);
        DateRange dateRange = new DateRange("", "");

        assertEquals(today.minusDays(180).toString("yyyy-MM-dd"), dateRange.getStartDateInSqlFormat());
        assertEquals(today.toString("yyyy-MM-dd"), dateRange.getEndDateInSqlFormat());
    }

    @Test
    public void shouldReturnStartAndEndDatesWhenOnlyStartDateIsGiven() {
        LocalDate startDate = new LocalDate(2012, 12, 12);

        DateRange dateRange = new DateRange(startDate.toString("dd/MM/yyyy"), null);

        assertEquals(startDate.toString("yyyy-MM-dd"), dateRange.getStartDateInSqlFormat());
        assertEquals(startDate.plusDays(180).toString("yyyy-MM-dd"), dateRange.getEndDateInSqlFormat());
    }

    @Test
    public void shouldReturnStartAndEndDatesWhenOnlyEndDateIsGiven() {
        LocalDate endDate = new LocalDate(2012, 12, 12);

        DateRange dateRange = new DateRange(null, endDate.toString("dd/MM/yyyy"));

        assertEquals(endDate.minusDays(180).toString("yyyy-MM-dd"), dateRange.getStartDateInSqlFormat());
        assertEquals(endDate.toString("yyyy-MM-dd"), dateRange.getEndDateInSqlFormat());
    }

}
