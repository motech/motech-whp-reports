package org.motechproject.whp.reports.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.whp.reports.contract.provider.ProviderDTO;
import org.motechproject.whp.reports.domain.dimension.Provider;
import org.motechproject.whp.reports.mapper.ProviderMapper;
import org.motechproject.whp.reports.repository.ProviderRepository;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class ProviderServiceTest {

    ProviderService providerService;

    @Mock
    ProviderRepository providerRepository;

    @Mock
    ProviderMapper providerMapper;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        providerService = new ProviderService(providerRepository, providerMapper);
    }

    @Test
    public void shouldGetProviderByProviderID() {
        String providerId = "providerId";
        Provider expectedProvider = mock(Provider.class);

        when(providerRepository.findByProviderId(providerId)).thenReturn(expectedProvider);
        Provider provider = providerService.getProvider(providerId);

        assertEquals(expectedProvider, provider);
    }

    @Test
    public void shouldUpdateProvider_whenProviderExists() {
        ProviderDTO providerDTO = new ProviderDTO();
        providerDTO.setProviderId("providerId");

        Provider provider = mock(Provider.class);
        when(providerRepository.findByProviderId(providerDTO.getProviderId())).thenReturn(provider);

        providerService.save(providerDTO);

        verify(providerMapper).map(providerDTO, provider);
        verify(providerRepository).save(provider);
    }

    @Test
    public void shouldCreateProvider_whenProviderDoesNotExistForGivenProviderId() {
        ProviderDTO providerDTO = new ProviderDTO();
        providerDTO.setProviderId("providerId");

        Provider expectedProvider = new Provider();

        when(providerRepository.findByProviderId(providerDTO.getProviderId())).thenReturn(null);

        providerService.save(providerDTO);

        verify(providerMapper).map(providerDTO, expectedProvider);
        verify(providerRepository).save(expectedProvider);
    }

    @Test
    public void shouldFindAllProviders(){
        Provider provider1 = mock(Provider.class);
        Provider provider2 = mock(Provider.class);
        Provider provider3 = mock(Provider.class);
        ArrayList<Provider> expectedProviders = new ArrayList<>();
        expectedProviders.addAll(asList(provider1, provider2, provider3));

        when(providerRepository.findAll()).thenReturn(expectedProviders);
        List<Provider> providers = providerService.findAll();

        assertEquals(expectedProviders, providers);

    }
}
