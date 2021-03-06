package org.motechproject.whp.reports.query;

import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.motechproject.bigquery.model.FilterParams;
import org.motechproject.bigquery.response.QueryResult;
import org.motechproject.bigquery.service.BigQueryService;
import org.motechproject.whp.reports.IntegrationTest;
import org.motechproject.whp.reports.builder.PatientBuilder;
import org.motechproject.whp.reports.domain.dimension.District;
import org.motechproject.whp.reports.domain.patient.Patient;
import org.motechproject.whp.reports.domain.patient.Treatment;
import org.motechproject.whp.reports.repository.DistrictRepository;
import org.motechproject.whp.reports.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.util.LinkedCaseInsensitiveMap;

import java.util.List;
import java.util.Map;

import static ch.lambdaj.Lambda.extract;
import static ch.lambdaj.Lambda.on;
import static java.util.Arrays.asList;
import static junit.framework.Assert.assertEquals;
import static org.motechproject.whp.reports.builder.PatientBuilder.defaultTreatment;
import static org.motechproject.whp.reports.date.WHPDate.toSqlDate;

public class TBRegistrationsByDistrictQueryIT extends IntegrationTest {

    @Autowired
    BigQueryService queryService;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    DistrictRepository districtRepository;
    private List<String> allDistrictNames;

    @Before
    public void setUp() {
        List<District> districtList = districtRepository.findAll(new Sort(new Sort.Order(Sort.Direction.ASC, "name")));
        allDistrictNames = extract(districtList, on(District.class).getName());
    }

    @Test
    public void shouldGetCountOfTBRegistrations() {
        Treatment treatment2 = defaultTreatment();
        treatment2.setProviderDistrict("Bhagalpur");

        Patient patient1 = new PatientBuilder().withDefaults().withPatientId("patient1").withTbId("tb1").withDistrict("Begusarai")
                .addTreatment(treatment2).withTbId("tb2")
                .build();
        Patient patient2 = new PatientBuilder().withDefaults().withPatientId("patient2").withTbId("tb3").withDistrict("Bhagalpur").build();

        patientRepository.save(asList(patient1, patient2));

        QueryResult queryResult = queryService.executeQuery("number.of.tb.registrations.by.district", new FilterParams());

        QueryResult expectedResult = new QueryResultBuilder("district", "tb_registration_count")
                .row("Begusarai", 1L)
                .row("Bhagalpur", 2L)
                .build(allDistrictNames);
        assertEquals(expectedResult, queryResult);
    }

    @Test
    public void shouldGetCountOfTBRegistrationsWithDateRangeFilter() {
        Treatment treatment2 = defaultTreatment();
        treatment2.setProviderDistrict("Bhagalpur");
        treatment2.setStartDate(toSqlDate(new LocalDate(2013, 02, 14)));

        Patient patient1 = new PatientBuilder().withDefaults().withPatientId("patient1")
                .withTbRegistrationDate(new LocalDate(2013, 01, 01))
                .withTbId("tb1")
                .withDistrict("Begusarai")
                .addTreatment(treatment2).withTbId("tb2")
                .build();

        Patient patient2 = new PatientBuilder().withDefaults().withPatientId("patient2").withTbId("tb3")
                .withTbRegistrationDate(new LocalDate(2013, 02, 14))
                .withDistrict("Begusarai").build();

        patientRepository.save(asList(patient1, patient2));

        FilterParams filterParams = new FilterParams();
        filterParams.put("from_date", "02/02/2013");
        filterParams.put("to_date", "02/03/2013");
        QueryResult queryResult = queryService.executeQuery("number.of.tb.registrations.by.district", filterParams);

        QueryResult expectedResult = new QueryResultBuilder("district", "tb_registration_count")
                .row("Begusarai", 1L)
                .row("Bhagalpur", 1L)
                .build(allDistrictNames);

        assertEquals(expectedResult, queryResult);
    }

    protected Map<String, Object> row(String dimension, int count) {
        Map<String, Object> row1 = new LinkedCaseInsensitiveMap<>();
        row1.put("district", dimension);
        row1.put("tb_registration_count", Long.valueOf(count));
        return row1;
    }

    @After
    public void tearDown() {
        patientRepository.deleteAll();
    }
}

