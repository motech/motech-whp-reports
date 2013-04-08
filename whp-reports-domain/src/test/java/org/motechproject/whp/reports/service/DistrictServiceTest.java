package org.motechproject.whp.reports.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.whp.reports.domain.dimension.District;
import org.motechproject.whp.reports.repository.DistrictRepository;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class DistrictServiceTest {

    DistrictService districtService;

    @Mock
    DistrictRepository districtRepository;

    @Before
    public void setUp(){
       initMocks(this);
       districtService  = new DistrictService(districtRepository);
    }

    @Test
    public void shouldCallFindAllDistricts() {

        District district = new District();
        when(districtRepository.findAll()).thenReturn(asList(district));

       List<District> districts = districtService.getAllDistricts();
        verify(districtRepository).findAll();
        assertThat(districts.get(0), is(district));


  }

}
