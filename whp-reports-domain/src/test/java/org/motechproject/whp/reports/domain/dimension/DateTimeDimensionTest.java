package org.motechproject.whp.reports.domain.dimension;

import junit.framework.Assert;
import org.joda.time.DateTime;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

// Test class to verify the DateTimeDimension dimension behaviour
public class DateTimeDimensionTest {

    @Test
    public void shouldCompareBySeconds() {
        DateTimeDimension past = new DateTimeDimension((new DateTime(1, 1, 1, 1, 1, 10)).toDate());
        DateTimeDimension present = new DateTimeDimension((new DateTime(1, 1, 1, 1, 1, 30)).toDate());
        DateTimeDimension future = new DateTimeDimension((new DateTime(1, 1, 1, 1, 1, 50)).toDate());
        verifyDatePrecedence(past, present, future);
    }

    @Test
    public void shouldCompareByMinutes() {
        DateTimeDimension past = new DateTimeDimension((new DateTime(1, 1, 1, 1, 10, 1)).toDate());
        DateTimeDimension present = new DateTimeDimension((new DateTime(1, 1, 1, 1, 30, 1)).toDate());
        DateTimeDimension future = new DateTimeDimension((new DateTime(1, 1, 1, 1, 50, 1)).toDate());
        verifyDatePrecedence(past, present, future);
    }

    @Test
    public void shouldCompareByHours() {
        DateTimeDimension past = new DateTimeDimension((new DateTime(1, 1, 1, 1, 1, 1)).toDate());
        DateTimeDimension present = new DateTimeDimension((new DateTime(1, 1, 1, 5, 1, 1)).toDate());
        DateTimeDimension future = new DateTimeDimension((new DateTime(1, 1, 1, 10, 1, 1)).toDate());
        verifyDatePrecedence(past, present, future);
    }

    @Test
    public void shouldCompareByDays() {
        DateTimeDimension past = new DateTimeDimension((new DateTime(1, 1, 1, 1, 1, 1)).toDate());
        DateTimeDimension present = new DateTimeDimension((new DateTime(1, 1, 10, 1, 1, 1)).toDate());
        DateTimeDimension future = new DateTimeDimension((new DateTime(1, 1, 25, 1, 1, 1)).toDate());
        verifyDatePrecedence(past, present, future);
    }

    @Test
    public void shouldCompareByMonths() {
        DateTimeDimension past = new DateTimeDimension((new DateTime(1, 1, 1, 1, 1, 1)).toDate());
        DateTimeDimension present = new DateTimeDimension((new DateTime(1, 6, 1, 1, 1, 1)).toDate());
        DateTimeDimension future = new DateTimeDimension((new DateTime(1, 11, 1, 1, 1, 1)).toDate());
        verifyDatePrecedence(past, present, future);
    }

    @Test
    public void shouldCompareByYears() {
        DateTimeDimension past = new DateTimeDimension((new DateTime(2000, 1, 1, 1, 1, 1)).toDate());
        DateTimeDimension present = new DateTimeDimension((new DateTime(2012, 1, 1, 1, 1, 1)).toDate());
        DateTimeDimension future = new DateTimeDimension((new DateTime(2112, 1, 1, 1, 1, 1)).toDate());
        verifyDatePrecedence(past, present, future);
    }

    @Test
    public void shouldComputeProperDifferenceInSeconds() {
        DateTimeDimension begin = new DateTimeDimension((new DateTime(1, 1, 1, 1, 1, 0)).toDate());
        DateTimeDimension end = new DateTimeDimension((new DateTime(1, 1, 1, 1, 1, 45)).toDate());
        assertEquals(45, begin.differenceInSeconds(end));

        begin = new DateTimeDimension((new DateTime(1, 1, 1, 1, 1, 1)).toDate());
        end = new DateTimeDimension((new DateTime(1, 1, 1, 1, 2, 1)).toDate());
        assertEquals(60, begin.differenceInSeconds(end));

        begin = new DateTimeDimension((new DateTime(1, 1, 1, 1, 1, 1)).toDate());
        end = new DateTimeDimension((new DateTime(1, 1, 1, 2, 1, 1)).toDate());
        assertEquals(3600, begin.differenceInSeconds(end));
    }

    private void verifyDatePrecedence(DateTimeDimension past, DateTimeDimension present, DateTimeDimension future) {
        Assert.assertEquals(-1, past.compareTo(present));
        Assert.assertEquals(-1, present.compareTo(future));
        Assert.assertEquals(-1, past.compareTo(future));
    }
}
