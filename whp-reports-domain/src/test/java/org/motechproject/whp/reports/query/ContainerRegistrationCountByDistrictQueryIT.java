package org.motechproject.whp.reports.query;

import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.motechproject.bigquery.model.FilterParams;
import org.motechproject.bigquery.response.QueryResult;
import org.motechproject.bigquery.service.BigQueryService;
import org.motechproject.whp.reports.IntegrationTest;
import org.motechproject.whp.reports.builder.ContainerRecordBuilder;
import org.motechproject.whp.reports.domain.dimension.District;
import org.motechproject.whp.reports.domain.measure.container.ContainerRecord;
import org.motechproject.whp.reports.repository.ContainerRecordRepository;
import org.motechproject.whp.reports.repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import java.util.List;

import static ch.lambdaj.Lambda.extract;
import static ch.lambdaj.Lambda.on;
import static java.util.Arrays.asList;
import static junit.framework.Assert.assertEquals;

public class ContainerRegistrationCountByDistrictQueryIT extends IntegrationTest {

    public static final String QUERY = "number.of.container.registrations.by.district";
    @Autowired
    BigQueryService queryService;
    @Autowired
    ContainerRecordRepository containerRecordRepository;
    @Autowired
    DistrictRepository districtRepository;

    private final FilterParams emptyFilterParams = new FilterParams();
    private List<String> allDistrictNames;

    @Before
    public void setUp() {
        List<District> districtList = districtRepository.findAll(new Sort(new Sort.Order(Sort.Direction.ASC, "name")));
        allDistrictNames = extract(districtList, on(District.class).getName());
    }

    @Test
    public void shouldReturnCountOfContainerRegistrations() {
        ContainerRecord containerRecord1 = createContainer("container1", "Patna", new LocalDate(2013, 10, 12), "Open");
        ContainerRecord containerRecord2 = createContainer("container2", "Patna", new LocalDate(2013, 11, 12), "Closed");
        ContainerRecord containerRecord3 = createContainer("container3", "Begusarai", new LocalDate(2013, 12, 12), "Open");
        ContainerRecord containerRecord4 = createContainer("container4", "Begusarai", new LocalDate(2013, 12, 12), "Open");
        ContainerRecord containerRecord5 = createContainer("container5", "West Champaran", new LocalDate(2013, 12, 12), "Closed");

        containerRecordRepository.save(asList(containerRecord1, containerRecord2, containerRecord3, containerRecord4, containerRecord5));

        QueryResult queryResult = queryService.executeQuery(QUERY, emptyFilterParams);

        QueryResult expectedQueryResult = new QueryResultBuilder("district", "active", "closed")
                .row("Begusarai", 2L, 0L)
                .row("Patna", 1L, 1L)
                .row("West Champaran", 0L, 1L)
                .build(allDistrictNames);

        assertEquals(expectedQueryResult, queryResult);
    }

    @Test
    public void shouldReturnCountOfContainerRegistrationsForGivenFilters() {
        ContainerRecord containerRecord1 = createContainer("container1", "Patna", new LocalDate(2013, 10, 12), "Open");
        ContainerRecord containerRecord2 = createContainer("container2", "Patna", new LocalDate(2013, 11, 12), "Closed");
        ContainerRecord containerRecord3 = createContainer("container3", "Begusarai", new LocalDate(2013, 12, 12), "Open");
        ContainerRecord containerRecord4 = createContainer("container4", "Begusarai", new LocalDate(2013, 12, 13), "Open");
        ContainerRecord containerRecord5 = createContainer("container5", "West Champaran", new LocalDate(2013, 12, 14), "Closed");

        containerRecordRepository.save(asList(containerRecord1, containerRecord2, containerRecord3, containerRecord4, containerRecord5));

        FilterParams filterParamsWithDateRange = new FilterParams();
        filterParamsWithDateRange.put("from_date", "12/12/2013");
        filterParamsWithDateRange.put("to_date", "14/12/2013");

        QueryResult queryResult = queryService.executeQuery(QUERY, filterParamsWithDateRange);

        QueryResult expectedQueryResult = new QueryResultBuilder("district", "active", "closed")
                .row("Begusarai", 2L, 0L)
                .row("West Champaran", 0L, 1L)
                .build(allDistrictNames);

        assertEquals(expectedQueryResult, queryResult);
    }

    private ContainerRecord createContainer(String containerId, String district, LocalDate issuedOnDate, String status) {
        return new ContainerRecordBuilder().withDefaults()
                .withContainerId(containerId)
                .withProviderDistrict(district)
                .withStatus(status)
                .withIssuedOnDate(issuedOnDate.toDate())
                .build();
    }

    @After
    public void tearDown() {
        containerRecordRepository.deleteAll();
    }

}
