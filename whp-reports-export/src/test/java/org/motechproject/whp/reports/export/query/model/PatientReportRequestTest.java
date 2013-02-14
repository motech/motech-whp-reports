package org.motechproject.whp.reports.export.query.model;

import org.junit.Test;

public class PatientReportRequestTest {
    @Test
    public void shouldReturnStartAndEndDatesFromGivenFromDateAndToDate() {
        PatientReportRequest request = new PatientReportRequest();
        request.setFrom("20/12/2012");
        request.setTo("30/12/2012");

//        assertEquals("2012-12-20", request.g);
    }
}
