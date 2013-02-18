package org.motechproject.whp.reports.mapper;

import org.motechproject.whp.reports.contract.provider.ProviderDTO;
import org.motechproject.whp.reports.domain.dimension.Provider;
import org.springframework.stereotype.Component;

@Component
public class ProviderMapper {
    public void map(ProviderDTO providerDTO, Provider provider) {
        provider.setProviderId(providerDTO.getProviderId());
        provider.setDistrict(providerDTO.getDistrict());
        provider.setPrimaryMobile(providerDTO.getPrimaryMobile());
        provider.setSecondaryMobile(providerDTO.getSecondaryMobile());
        provider.setTertiaryMobile(providerDTO.getTertiaryMobile());
    }

}
