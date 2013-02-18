package org.motechproject.whp.reports.service;

import org.motechproject.whp.reports.contract.provider.ProviderDTO;
import org.motechproject.whp.reports.domain.dimension.Provider;
import org.motechproject.whp.reports.mapper.ProviderMapper;
import org.motechproject.whp.reports.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProviderService {

    private ProviderRepository providerRepository;
    private ProviderMapper providerMapper;

    ProviderService() {
    }

    @Autowired
    public ProviderService(ProviderRepository providerRepository, ProviderMapper providerMapper) {
        this.providerRepository = providerRepository;
        this.providerMapper = providerMapper;
    }

    public Provider getProvider(String providerId) {
        return providerRepository.findByProviderId(providerId);
    }


    public void save(ProviderDTO providerDTO) {
        Provider provider = providerRepository.findByProviderId(providerDTO.getProviderId());

        if(provider == null){
            provider = new Provider();
        }

        providerMapper.map(providerDTO, provider);
        providerRepository.save(provider);
    }
}
