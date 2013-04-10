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
import org.springframework.util.LinkedCaseInsensitiveMap;

import java.util.Map;

import static java.util.Arrays.asList;
import static junit.framework.Assert.assertEquals;
import static org.motechproject.whp.reports.builder.PatientBuilder.defaultTreatment;
import static org.motechproject.whp.reports.date.WHPDate.toSqlDate;

public class TBRegistrationsByProviderByDistrictQueryIT extends IntegrationTest{

    @Autowired
    BigQueryService queryService;
    @Autowired
    PatientRepository patientRepository;

    @Test
    public void shouldGetCountOfTBRegistrations() {
        Treatment treatment2 = defaultTreatment();
        treatment2.setProviderDistrict("district2");
        treatment2.setProviderId("p2");
        Patient patient1 = new PatientBuilder().withDefaults().withPatientId("patient1").withTbId("tb1").withDistrict("district1").withProviderId("p1")
                .addTreatment(treatment2).withTbId("tb2")
                .build();
        Patient patient2 = new PatientBuilder().withDefaults().withPatientId("patient2").withTbId("tb3").withDistrict("district2").withProviderId("p2").build();
        Patient patient3 = new PatientBuilder().withDefaults().withPatientId("patient3").withTbId("tb4").withDistrict("district2").withProviderId("p3").build();

        patientRepository.save(asList(patient1, patient2, patient3));

        QueryResult queryResult = queryService.executeQuery("number.of.tb.registrations.by.provider.by.district", new FilterParams());

        QueryResult expectedResult = new QueryResult(asList(row("district2", 2), row("district1", 1)));
        assertEquals(expectedResult, queryResult);
    }

    @Test
    public void shouldGetCountOfTBRegistrationsWithDateRangeFilter() {
        Treatment treatment2 = defaultTreatment();
        treatment2.setProviderDistrict("district2");
        treatment2.setProviderId("p2");
        treatment2.setStartDate(toSqlDate(new LocalDate(2013, 02, 14)));

        Patient patient1 = new PatientBuilder().withDefaults().withPatientId("patient1")
                .withTbRegistrationDate(new LocalDate(2013, 01, 01))
                .withTbId("tb1")
                .withDistrict("district1")
                .withProviderId("p1")
                .addTreatment(treatment2).withTbId("tb2")
                .build();

        Patient patient2 = new PatientBuilder().withDefaults().withPatientId("patient2").withTbId("tb3")
                .withTbRegistrationDate(new LocalDate(2013, 02, 14))
                .withDistrict("district1").withProviderId("p1").build();

        patientRepository.save(asList(patient1, patient2));

        FilterParams filterParams = new FilterParams();
        filterParams.put("from_date", "02/02/2013");
        filterParams.put("to_date", "02/03/2013");
        QueryResult queryResult = queryService.executeQuery("number.of.tb.registrations.by.provider.by.district", filterParams);

        QueryResult expectedResult = new QueryResult(asList(row("district1", 1), row("district2", 1)));
        assertEquals(expectedResult, queryResult);
    }

    private Map<String, Object> row(String outcome, int count) {
        Map<String, Object> row1 = new LinkedCaseInsensitiveMap<>();
        row1.put("provider_count", Long.valueOf(count));
        row1.put("district", outcome);
        return row1;
    }

    @After
    public void tearDown() {
        patientRepository.deleteAll();
    }
}

