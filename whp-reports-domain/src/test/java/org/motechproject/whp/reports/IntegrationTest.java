package org.motechproject.whp.reports;

import org.junit.runner.RunWith;
import org.motechproject.testing.utils.BaseUnitTest;
import org.motechproject.whp.reports.domain.dimension.Provider;
import org.motechproject.whp.reports.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationReportingDomainContext.xml")
public abstract class IntegrationTest extends BaseUnitTest {


    @Autowired
    public ProviderRepository providerRepository;

    public Provider createProvider(String providerId, String district) {
        Provider provider = new Provider();
        provider.setProviderId(providerId);
        provider.setDistrict(district);
        return provider;
    }

}