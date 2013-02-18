package org.motechproject.whp.reports.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.motechproject.whp.reports.IntegrationTest;
import org.motechproject.whp.reports.domain.dimension.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ProviderRepositoryIT extends IntegrationTest {

    @Autowired
    ProviderRepository providerRepository;

    @Autowired
    PlatformTransactionManager transactionManager;

    TransactionTemplate transactionTemplate;
    Provider provider;
    String providerId = "providerId";

    @Before
    public void setUp() throws Exception {
        this.transactionTemplate = new TransactionTemplate(transactionManager);

        provider = new Provider();
        provider.setProviderId(providerId);
        provider.setDistrict("district");
        provider.setPrimaryMobile("primary");
        provider.setSecondaryMobile("secondary");
        provider.setTertiaryMobile("tertiary");
    }

    @Test
    public void shouldCreateProvider() {
        providerRepository.save(provider);
        final Long id = provider.getId();

        assertNotNull(id);

        transactionTemplate.execute(new TransactionCallback() {
            public Object doInTransaction(TransactionStatus status) {
                Provider providerFromDB = providerRepository.findOne(id);
                assertEquals(provider.getProviderId(), providerFromDB.getProviderId());
                assertEquals(provider.getDistrict(),providerFromDB.getDistrict());
                assertEquals(provider.getPrimaryMobile(),providerFromDB.getPrimaryMobile());
                assertEquals(provider.getSecondaryMobile(),providerFromDB.getSecondaryMobile());
                assertEquals(provider.getTertiaryMobile(),providerFromDB.getTertiaryMobile());
                return null;
            }
        });
    }

    @Test
    @Transactional
    public void shouldReturnProviderByProviderId() {
        providerRepository.save(provider);
        Provider providerFromDB = providerRepository.findByProviderId(providerId);

        assertNotNull(providerId);
        assertEquals(provider.getId(), providerFromDB.getId());
    }

    @After
    public void tearDown() {
        providerRepository.deleteAll();
    }
}
