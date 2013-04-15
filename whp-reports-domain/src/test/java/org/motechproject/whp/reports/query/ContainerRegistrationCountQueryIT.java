package org.motechproject.whp.reports.query;

import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Test;
import org.motechproject.bigquery.model.FilterParams;
import org.motechproject.bigquery.response.QueryResult;
import org.motechproject.bigquery.service.BigQueryService;
import org.motechproject.whp.reports.IntegrationTest;
import org.motechproject.whp.reports.builder.ContainerRecordBuilder;
import org.motechproject.whp.reports.domain.measure.container.ContainerRecord;
import org.motechproject.whp.reports.repository.ContainerRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;

import static java.util.Arrays.asList;
import static junit.framework.Assert.assertEquals;

public class ContainerRegistrationCountQueryIT extends IntegrationTest {

    @Autowired
    BigQueryService queryService;
    @Autowired
    ContainerRecordRepository containerRecordRepository;

    private final FilterParams emptyFilterParams = new FilterParams();

    @Test
    public void shouldReturnCountOfContainerRegistrations() {
        ContainerRecord containerRecord1 = createContainer("containerId1", "Patna", new LocalDate(2013, 10, 12));
        ContainerRecord containerRecord2 = createContainer("containerId2", "Patna", new LocalDate(2013, 11, 12));
        ContainerRecord containerRecord3 = createContainer("containerId3", "Begusarai", new LocalDate(2013, 12, 12));

        containerRecordRepository.save(asList(containerRecord1, containerRecord2, containerRecord3));

        assertQueryResults(3L, emptyFilterParams);

        FilterParams filterParamsWithDistrict = new FilterParams();
        filterParamsWithDistrict.put("district", "Patna");

        assertQueryResults(2L, filterParamsWithDistrict);

        FilterParams filterParamsWithDate = new FilterParams();
        filterParamsWithDate.put("from_date", "10/11/2013");
        filterParamsWithDate.put("to_date", "15/12/2013");

        assertQueryResults(2L, filterParamsWithDate);

        FilterParams filterParamsWithDateAndDistrict = new FilterParams();
        filterParamsWithDateAndDistrict.put("from_date", "10/11/2013");
        filterParamsWithDateAndDistrict.put("to_date", "15/12/2013");
        filterParamsWithDateAndDistrict.put("district", "Begusarai");

        assertQueryResults(1L, filterParamsWithDateAndDistrict);
    }

    private ContainerRecord createContainer(String containerId, String district, LocalDate issuedOnDate) {
        return new ContainerRecordBuilder().withDefaults()
                .withContainerId(containerId)
                .withProviderDistrict(district)
                .withIssuedOnDate(issuedOnDate.toDate())
                .build();
    }

    @After
    public void tearDown() {
        containerRecordRepository.deleteAll();
    }

    private void assertQueryResults(long expectedTbCount, FilterParams filterParams) {
        QueryResult queryResult = queryService.executeQuery("number.of.container.registrations", filterParams);
        assertQueryResult(queryResult, expectedTbCount);
    }

    private void assertQueryResult(QueryResult queryResult, long expectedTbCount) {
        assertEquals(1, queryResult.getContent().size());
        assertEquals(expectedTbCount, queryResult.getContent().get(0).get("container_registration_count"));
    }

}
