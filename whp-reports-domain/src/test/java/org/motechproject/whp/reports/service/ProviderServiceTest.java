package org.motechproject.whp.reports.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.whp.reports.domain.dimension.Provider;
import org.motechproject.whp.reports.repository.ProviderRepository;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ProviderServiceTest {

    ProviderService providerService;

    @Mock
    ProviderRepository providerRepository;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        providerService = new ProviderService(providerRepository);
    }

    @Test
    public void shouldGetProviderByProviderID() {
        String providerId = "providerId";
        Provider expectedProvider = mock(Provider.class);

        when(providerRepository.findByProviderId(providerId)).thenReturn(expectedProvider);
        Provider provider = providerService.getProvider(providerId);

        assertEquals(expectedProvider, provider);

    }
}
