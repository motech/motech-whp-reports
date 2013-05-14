package org.motechproject.whp.reports.repository;

import org.junit.Test;
import org.motechproject.whp.reports.IntegrationTest;
import org.motechproject.whp.reports.domain.dimension.District;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class DistrictRepositoryIT extends IntegrationTest {

    @Autowired
    DistrictRepository districtRepository;

    @Test
    public void shouldFindDistrictByName() {
        District expectedDistrict = new District();
        expectedDistrict.setName("expected");
        districtRepository.save(expectedDistrict);

        List<District> actualDistrictList = districtRepository.findByName("expected");

        assertEquals(asList(expectedDistrict), actualDistrictList);
    }
}
