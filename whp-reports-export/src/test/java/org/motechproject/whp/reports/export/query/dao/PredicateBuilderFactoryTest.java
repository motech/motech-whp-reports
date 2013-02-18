package org.motechproject.whp.reports.export.query.dao;

import org.junit.Before;
import org.junit.Test;
import org.motechproject.whp.reports.export.query.model.PatientReportRequest;

import static junit.framework.Assert.assertEquals;

public class PredicateBuilderFactoryTest {
    private PatientReportRequest patientReportRequest;
    private PredicateBuilderFactory predicateBuilderFactory;

    @Before
    public void setUp() {
        patientReportRequest = new PatientReportRequest();
        patientReportRequest.setDistrict("d1");
        patientReportRequest.setFrom("25/12/2012");
        patientReportRequest.setTo("25/12/2013");
        patientReportRequest.setReportType(PatientReportType.SUMMARY_REPORT);
        predicateBuilderFactory = new PredicateBuilderFactory();
    }

    @Test
    public void shouldReturnSummaryPredicateBuilderGivenReportType() {
        PatientSummaryPredicateBuilder patientSummaryPredicateBuilder = new PatientSummaryPredicateBuilder(patientReportRequest);
        assertEquals(patientSummaryPredicateBuilder, predicateBuilderFactory.getBuilder(patientReportRequest));
    }

    @Test
    public void shouldReturnCloseTreatmentPredicateBuilderGivenReportType() {
        patientReportRequest.setReportType(PatientReportType.CLOSED_TREATMENT);
        PatientCloseTreatmentPredicateBuilder patientCloseTreatmentPredicateBuilder = new PatientCloseTreatmentPredicateBuilder(patientReportRequest);

        assertEquals(patientCloseTreatmentPredicateBuilder, predicateBuilderFactory.getBuilder(patientReportRequest));
    }
}
