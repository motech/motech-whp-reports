package org.motechproject.whp.reports.domain;

import org.joda.time.LocalDate;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TreatmentWeekTest {

    @Test
    public void shouldReturnNextTreatmentWeek() {
        TreatmentWeek treatmentWeek = new TreatmentWeek(new LocalDate(2013, 1, 1));

        treatmentWeek.moveToNextWeek();
        assertEquals(new LocalDate(2013, 1, 8), treatmentWeek.getReference());

        treatmentWeek.moveToNextWeek();
        assertEquals(new LocalDate(2013, 1, 15), treatmentWeek.getReference());
    }

}
