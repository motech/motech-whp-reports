package org.motechproject.whp.reports.export.query.dao;

import org.junit.Test;
import org.motechproject.whp.reports.export.query.dao.query.builder.PatientSummaryPredicateBuilder;
import org.motechproject.whp.reports.export.query.model.PatientReportRequest;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PatientSummaryPredicateBuilderTest {

    @Test
    public void shouldReturnPredicateToFilterByTreatmentStartDate(){
        PatientReportRequest patientReportRequest = new PatientReportRequest();
        patientReportRequest.setReportType(PatientReportType.SUMMARY_REPORT);
        patientReportRequest.setFrom("25/12/2012");
        patientReportRequest.setTo("25/12/2013");
        PatientSummaryPredicateBuilder patientSummaryPredicateBuilder = new PatientSummaryPredicateBuilder(patientReportRequest);

        List<String> expectedPredicates = asList(" treatment.start_date between '2012-12-25' AND '2013-12-25'");
        assertThat(patientSummaryPredicateBuilder.getPredicates(), is(expectedPredicates));
    }

    @Test
    public void shouldReturnEmptyPredicateWhenDateRangeIsNotPresent(){
        PatientReportRequest patientReportRequest = new PatientReportRequest();
        patientReportRequest.setReportType(PatientReportType.SUMMARY_REPORT);
        patientReportRequest.setFrom(null);
        patientReportRequest.setTo(null);

        PatientSummaryPredicateBuilder patientSummaryPredicateBuilder = new PatientSummaryPredicateBuilder(patientReportRequest);

        List<String> emptyPredicates = new ArrayList<>();
        assertThat(patientSummaryPredicateBuilder.getPredicates(), is(emptyPredicates));
    }
}
