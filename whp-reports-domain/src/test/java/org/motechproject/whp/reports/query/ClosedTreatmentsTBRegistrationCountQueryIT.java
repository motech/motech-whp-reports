package org.motechproject.whp.reports.query;

import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Test;
import org.motechproject.bigquery.model.FilterParams;
import org.motechproject.bigquery.response.QueryResult;
import org.motechproject.bigquery.service.BigQueryService;
import org.motechproject.whp.reports.IntegrationTest;
import org.motechproject.whp.reports.builder.PatientBuilder;
import org.motechproject.whp.reports.domain.patient.Patient;
import org.motechproject.whp.reports.domain.patient.Treatment;
import org.motechproject.whp.reports.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static java.util.Arrays.asList;
import static junit.framework.Assert.assertEquals;
import static org.motechproject.whp.reports.builder.PatientBuilder.defaultTreatment;
import static org.motechproject.whp.reports.date.WHPDate.toSqlDate;

public class ClosedTreatmentsTBRegistrationCountQueryIT extends IntegrationTest {

    @Autowired
    BigQueryService queryService;
    @Autowired
    PatientRepository patientRepository;

    @Test
    public void shouldGetCountOfTBRegistrationsGroupByTreatmentOutcome() {
        Patient patient1 = new PatientBuilder().withDefaults().withPatientId("patient1").withTreatmentOutcome("Cured").withTbId("tb1")
                .addTreatment(defaultTreatment()).withTreatmentOutcome("Died").withTbId("tb2")
                .build();
        Patient patient2 = new PatientBuilder().withDefaults().withPatientId("patient2").withTreatmentOutcome("Cured").withTbId("tb3").build();
        Patient patient3 = new PatientBuilder().withDefaults().withPatientId("patient3").withTreatmentOutcome("Treatment Completed").withTbId("tb4").build();
        patientRepository.save(asList(patient1, patient2, patient3));

        QueryResult queryResult = queryService.executeQuery("number.of.tb.registrations.by.outcome", new FilterParams());

        QueryResult expectedResult = new QueryResultBuilder("outcome", "tb_registration_count")
                .row("Cured", 2L)
                .row("Died", 1L)
                .row("Treatment Completed", 1L)
                .build(ALL_TB_OUTCOMES);

        assertEquals(expectedResult, queryResult);
    }

    @Test
    public void shouldGetCountOfTBRegistrationsGroupByTreatmentOutcomeWithDistrictFilter() {
        Treatment treatment2 = defaultTreatment();
        treatment2.setProviderDistrict("district2");

        String filteredDistrict = "district1";

        Patient patient1 = new PatientBuilder().withDefaults().withPatientId("patient1").withTbId("tb1").withTreatmentOutcome("Cured").withDistrict(filteredDistrict)
                .addTreatment(treatment2).withTreatmentOutcome("Cured").withTbId("tb2")
                .build();

        Patient patient2 = new PatientBuilder().withDefaults().withPatientId("patient2").withTbId("tb3").withTreatmentOutcome("Died").withDistrict(filteredDistrict).build();

        patientRepository.save(asList(patient1, patient2));

        FilterParams filterParams = new FilterParams();
        filterParams.put("district", filteredDistrict);
        QueryResult queryResult = queryService.executeQuery("number.of.tb.registrations.by.outcome", filterParams);

        QueryResult expectedResult = new QueryResultBuilder("outcome", "tb_registration_count")
                .row("Cured", 1L)
                .row("Died", 1L)
                .build(ALL_TB_OUTCOMES);

        assertEquals(expectedResult, queryResult);
    }

    @Test
    public void shouldGetCountOfTBRegistrationsGroupByTreatmentOutcomeWithDateRangeFilter() {
        Treatment treatment2 = defaultTreatment();
        treatment2.setProviderDistrict("district2");
        treatment2.setTreatmentOutcome("Cured");
        treatment2.setEndDate(toSqlDate(new LocalDate(2013, 02, 14)));
        String filteredDistrict = "district1";

        Patient patient1 = new PatientBuilder().withDefaults().withPatientId("patient1")
                .withTreatmentEndDate(toSqlDate(new LocalDate(2013, 01, 01)))
                .withTbId("tb1")
                .withTreatmentOutcome("Cured")
                .withDistrict(filteredDistrict)
                .addTreatment(treatment2).withTbId("tb2")
                .build();

        Patient patient2 = new PatientBuilder().withDefaults().withPatientId("patient2").withTbId("tb3")
                .withTreatmentEndDate(toSqlDate(new LocalDate(2013, 02, 14)))
                .withTreatmentOutcome("Died")
                .withDistrict(filteredDistrict).build();

        patientRepository.save(asList(patient1, patient2));

        FilterParams filterParams = new FilterParams();
        filterParams.put("from_date", "02/02/2013");
        filterParams.put("to_date", "02/03/2013");
        QueryResult queryResult = queryService.executeQuery("number.of.tb.registrations.by.outcome", filterParams);

        QueryResult expectedQueryResult = new QueryResultBuilder("outcome", "tb_registration_count")
                .row("Cured", 1L)
                .row("Died", 1L)
                .build(ALL_TB_OUTCOMES);

        assertEquals(expectedQueryResult, queryResult);
    }

    @Test
    public void shouldGetCountOfTBRegistrationsGroupByTreatmentOutcomeWithDistrictAndDateRangeFilter() {
        Treatment treatment2 = defaultTreatment();
        treatment2.setProviderDistrict("district2");
        treatment2.setEndDate(toSqlDate(new LocalDate(2013, 02, 14)));
        treatment2.setTreatmentOutcome("Cured");
        String filteredDistrict = "district1";

        Patient patient1 = new PatientBuilder().withDefaults().withPatientId("patient1")
                .withTreatmentEndDate(toSqlDate(new LocalDate(2013, 01, 01)))
                .withTbId("tb1")
                .withTreatmentOutcome("Cured")
                .withDistrict(filteredDistrict)
                .addTreatment(treatment2).withTbId("tb2")
                .build();

        Patient patient2 = new PatientBuilder().withDefaults().withPatientId("patient2").withTbId("tb3")
                .withTreatmentEndDate(toSqlDate(new LocalDate(2013, 02, 14)))
                .withTreatmentOutcome("Died")
                .withDistrict(filteredDistrict).build();

        patientRepository.save(asList(patient1, patient2));

        FilterParams filterParams = new FilterParams();
        filterParams.put("district", filteredDistrict);
        filterParams.put("from_date", "02/02/2013");
        filterParams.put("to_date", "02/03/2013");
        QueryResult queryResult = queryService.executeQuery("number.of.tb.registrations.by.outcome", filterParams);

        QueryResult expectedResult = new QueryResultBuilder("outcome", "tb_registration_count")
                .row("Died", 1L)
                .build(ALL_TB_OUTCOMES);

        assertEquals(expectedResult, queryResult);
    }

    private List<String> ALL_TB_OUTCOMES = asList("Cured", "Defaulted", "Died" , "Failure",
            "Switched Over To MDR-TB Treatment","Transferred Out", "Treatment Completed");

    @After
    public void tearDown() {
        patientRepository.deleteAll();
    }
}

