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

import static java.util.Arrays.asList;
import static junit.framework.Assert.assertEquals;
import static org.motechproject.whp.reports.builder.PatientBuilder.defaultTreatment;
import static org.motechproject.whp.reports.date.WHPDate.toSqlDate;

public class TBRegistrationCountQueryIT extends IntegrationTest{

    @Autowired
    BigQueryService queryService;
    @Autowired
    PatientRepository patientRepository;
    private final FilterParams emptyFilterParams = new FilterParams();

    @Test
    public void shouldGetCountOfTBRegistrations() {
        Patient patient1 = new PatientBuilder().withDefaults().withPatientId("patient1").withTbId("tb1")
                .addTreatment(defaultTreatment()).withTbId("tb2")
                .build();

        Patient patient2 = new PatientBuilder().withDefaults().withPatientId("patient2").withTbId("tb3").build();

        patientRepository.save(asList(patient1, patient2));

        assertQueryResults(3L, emptyFilterParams);
    }

    @Test
    public void shouldGetCountOfTBRegistrationsWithDistrictFilter() {
        Treatment treatment2 = defaultTreatment();
        treatment2.setProviderDistrict("district2");

        String filteredDistrict = "district1";

        Patient patient1 = new PatientBuilder().withDefaults().withPatientId("patient1").withTbId("tb1").withDistrict(filteredDistrict)
                .addTreatment(treatment2).withTbId("tb2")
                .build();

        Patient patient2 = new PatientBuilder().withDefaults().withPatientId("patient2").withTbId("tb3").withDistrict(filteredDistrict).build();

        patientRepository.save(asList(patient1, patient2));

        assertQueryResults(3L, emptyFilterParams);

        FilterParams filterParams = emptyFilterParams;
        filterParams.put("district", filteredDistrict);

        assertQueryResults(2L, filterParams);
    }

    @Test
    public void shouldGetCountOfTBRegistrationsWithDateRangeFilter() {
        Treatment treatment2 = defaultTreatment();
        treatment2.setProviderDistrict("district2");
        treatment2.setStartDate(toSqlDate(new LocalDate(2013, 02, 14)));
        String filteredDistrict = "district1";

        Patient patient1 = new PatientBuilder().withDefaults().withPatientId("patient1")
                .withTbRegistrationDate(new LocalDate(2013, 01, 01))
                .withTbId("tb1")
                .withDistrict(filteredDistrict)
                .addTreatment(treatment2).withTbId("tb2")
                .build();

        Patient patient2 = new PatientBuilder().withDefaults().withPatientId("patient2").withTbId("tb3")
                .withTbRegistrationDate(new LocalDate(2013, 02, 14))
                .withDistrict(filteredDistrict).build();

        patientRepository.save(asList(patient1, patient2));

        assertQueryResults(3L, emptyFilterParams);

        FilterParams filterParams = emptyFilterParams;
        filterParams.put("from_date", "02/02/2013");
        filterParams.put("to_date", "02/03/2013");

        assertQueryResults(2L, filterParams);
    }

    @Test
    public void shouldGetCountOfTBRegistrationsWithDistrictAndDateRangeFilter() {
        Treatment treatment2 = defaultTreatment();
        treatment2.setProviderDistrict("district2");
        treatment2.setStartDate(toSqlDate(new LocalDate(2013, 02, 14)));
        String filteredDistrict = "district1";

        Patient patient1 = new PatientBuilder().withDefaults().withPatientId("patient1")
                .withTbRegistrationDate(new LocalDate(2013, 01, 01))
                .withTbId("tb1")
                .withDistrict(filteredDistrict)
                .addTreatment(treatment2).withTbId("tb2")
                .build();

        Patient patient2 = new PatientBuilder().withDefaults().withPatientId("patient2").withTbId("tb3")
                .withTbRegistrationDate(new LocalDate(2013, 02, 14))
                .withDistrict(filteredDistrict).build();

        patientRepository.save(asList(patient1, patient2));

        assertQueryResults(3L, emptyFilterParams);

        FilterParams filterParams = emptyFilterParams;
        filterParams.put("district", filteredDistrict);
        filterParams.put("from_date", "02/02/2013");
        filterParams.put("to_date", "02/03/2013");

        assertQueryResults(1L, filterParams);
    }

    private void assertQueryResults(long expectedTbCount, FilterParams filterParams) {
        QueryResult queryResult = queryService.executeQuery("number.of.tb.registrations", filterParams);
        assertQueryResult(queryResult, expectedTbCount);
    }

    private void assertQueryResult(QueryResult queryResult, long expectedTbCount) {
        assertEquals(1, queryResult.getContent().size());
        assertEquals(expectedTbCount, queryResult.getContent().get(0).get("tb_registration_count"));
    }

    @After
    public void tearDown() {
        patientRepository.deleteAll();
    }
}

