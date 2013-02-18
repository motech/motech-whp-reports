package org.motechproject.whp.reports.service;

import org.motechproject.whp.reports.domain.dimension.Provider;
import org.motechproject.whp.reports.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProviderService {

    private ProviderRepository providerRepository;

    ProviderService() {
    }

    @Autowired
    public ProviderService(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;

    }

    public Provider getProvider(String providerId) {
        return providerRepository.findByProviderId(providerId);
    }


}
