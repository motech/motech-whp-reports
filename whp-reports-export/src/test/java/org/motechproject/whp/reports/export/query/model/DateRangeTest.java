package org.motechproject.whp.reports.export.query.model;

import org.joda.time.LocalDate;
import org.junit.Test;
import org.motechproject.testing.utils.BaseUnitTest;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DateRangeTest extends BaseUnitTest{


    @Test
    public void shouldReturnStartAndEndDatesInSqlDateFormatWithoutTimeStamp() {
        DateRange dateRange = new DateRange("12/12/2012", "25/12/2012", false);

        assertEquals("2012-12-12", dateRange.getStartDateInSqlFormat());
        assertEquals("2012-12-25", dateRange.getEndDateInSqlFormat());
    }

    @Test
    public void shouldReturnStartAndEndDatesInDefaultDateFormatWithoutTimeStamp() {
        DateRange dateRange = new DateRange("12/12/2012", "25/12/2012", false);

        assertEquals("12/12/2012", dateRange.getStartDate());
        assertEquals("25/12/2012", dateRange.getEndDate());
        assertTrue(dateRange.hasValidRange());
    }
    
    @Test
    public void shouldReturnStartAndEndDatesInSqlDateFormatwithTimestamp() {
        DateRange dateRange = new DateRange("12/12/2012", "25/12/2012", true);

        assertEquals("2012-12-12", dateRange.getStartDateInSqlFormat());
        assertEquals("2012-12-26", dateRange.getEndDateInSqlFormat());
    }

    @Test
    public void shouldReturnStartAndEndDatesInDefaultDateFormatwithTimestamp() {
        DateRange dateRange = new DateRange("12/12/2012", "25/12/2012", true);

        assertEquals("12/12/2012", dateRange.getStartDate());
        assertEquals("25/12/2012", dateRange.getEndDate());
        assertTrue(dateRange.hasValidRange());
    }

    @Test
    public void shouldCheckIfDateRangeExists() {
        DateRange dateRange = new DateRange(null, null, true);
        assertFalse(dateRange.hasValidRange());
    }

    @Test
    public void shouldReturnStartAndEndDatesWhenOnlyStartDateIsGiven() {
        LocalDate startDate = new LocalDate(2012, 12, 12);

        DateRange dateRange = new DateRange(startDate.toString("dd/MM/yyyy"), null, false);

        assertEquals(startDate.toString("yyyy-MM-dd"), dateRange.getStartDateInSqlFormat());
        assertEquals(startDate.plusDays(180).toString("yyyy-MM-dd"), dateRange.getEndDateInSqlFormat());
        assertTrue(dateRange.hasValidRange());
    }

    @Test
    public void shouldReturnStartAndEndDatesWhenOnlyEndDateIsGiven() {
        LocalDate endDate = new LocalDate(2012, 12, 12);

        DateRange dateRange = new DateRange(null, endDate.toString("dd/MM/yyyy"), false);

        assertEquals(endDate.minusDays(180).toString("yyyy-MM-dd"), dateRange.getStartDateInSqlFormat());
        assertEquals(endDate.toString("yyyy-MM-dd"), dateRange.getEndDateInSqlFormat());
        assertTrue(dateRange.hasValidRange());
    }

}
