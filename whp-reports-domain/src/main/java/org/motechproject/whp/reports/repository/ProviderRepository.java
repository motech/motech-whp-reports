package org.motechproject.whp.reports.repository;

import org.motechproject.whp.reports.domain.dimension.Provider;

public interface ProviderRepository extends MotechJpaRepository<Provider>{
    Provider findByProviderId(String patientId);
}
