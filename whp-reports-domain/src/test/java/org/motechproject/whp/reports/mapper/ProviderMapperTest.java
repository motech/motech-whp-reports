package org.motechproject.whp.reports.mapper;

import org.junit.Test;
import org.motechproject.whp.reports.contract.provider.ProviderDTO;
import org.motechproject.whp.reports.domain.dimension.Provider;

import static junit.framework.Assert.assertEquals;

public class ProviderMapperTest {

    ProviderMapper providerMapper;

    @Test
    public void shouldMapProviderDTOToProvider() {
        providerMapper = new ProviderMapper();
        ProviderDTO providerDTO = createProviderDTO();

        Provider provider = new Provider();
        providerMapper.map(providerDTO, provider);

        assertEquals(providerDTO.getDistrict(), provider.getDistrict());
        assertEquals(providerDTO.getProviderId(), provider.getProviderId());
        assertEquals(providerDTO.getPrimaryMobile(), provider.getPrimaryMobile());
        assertEquals(providerDTO.getSecondaryMobile(), provider.getSecondaryMobile());
        assertEquals(providerDTO.getTertiaryMobile(), provider.getTertiaryMobile());
    }

    private ProviderDTO createProviderDTO() {
        ProviderDTO providerDTO = new ProviderDTO();
        providerDTO.setProviderId("providerId");
        providerDTO.setDistrict("district");
        providerDTO.setPrimaryMobile("primary");
        providerDTO.setSecondaryMobile("secondary");
        providerDTO.setTertiaryMobile("tertiary");
        return providerDTO;
    }
}
