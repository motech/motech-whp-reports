package org.motechproject.whp.reports.export.query.model;

import org.joda.time.LocalDate;
import org.junit.Test;
import org.motechproject.testing.utils.BaseUnitTest;

import static junit.framework.Assert.assertEquals;

public class DateRangeTest extends BaseUnitTest{


    @Test
    public void shouldReturnStartAndEndDatesInGivenDateFormat() {
        DateRange dateRange = new DateRange("12/12/2012", "25/12/2012");

        assertEquals("2012-12-12", dateRange.getStartDate());
        assertEquals("2012-12-25", dateRange.getEndDate());
    }

    @Test
    public void shouldReturnDefaultStartAndEndDates() {
        LocalDate today = new LocalDate(2012, 12, 12);
        mockCurrentDate(today);
        DateRange dateRange = new DateRange(null, null);

        assertEquals(today.minusDays(180).toString("yyyy-MM-dd"), dateRange.getStartDate());
        assertEquals(today.toString("yyyy-MM-dd"), dateRange.getEndDate());
    }

    @Test
    public void shouldReturnStartAndEndDatesWhenOnlyStartDateIsGiven() {
        LocalDate startDate = new LocalDate(2012, 12, 12);

        DateRange dateRange = new DateRange(startDate.toString("dd/MM/yyyy"), null);

        assertEquals(startDate.toString("yyyy-MM-dd"), dateRange.getStartDate());
        assertEquals(startDate.plusDays(180).toString("yyyy-MM-dd"), dateRange.getEndDate());
    }

    @Test
    public void shouldReturnStartAndEndDatesWhenOnlyEndDateIsGiven() {
        LocalDate endDate = new LocalDate(2012, 12, 12);

        DateRange dateRange = new DateRange(null, endDate.toString("dd/MM/yyyy"));

        assertEquals(endDate.minusDays(180).toString("yyyy-MM-dd"), dateRange.getStartDate());
        assertEquals(endDate.toString("yyyy-MM-dd"), dateRange.getEndDate());
    }

}
