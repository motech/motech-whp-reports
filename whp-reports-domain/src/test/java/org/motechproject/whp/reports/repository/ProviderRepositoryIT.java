package org.motechproject.whp.reports.repository;

import junit.framework.Assert;
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
        provider.setPrimaryMobile("primaryMobile");
    }

    @Test
    public void shouldCreateProvider() {
        providerRepository.save(provider);
        final Long providerId = provider.getId();

        assertNotNull(providerId);

        transactionTemplate.execute(new TransactionCallback() {
            public Object doInTransaction(TransactionStatus status) {
                Provider providerFromDB = providerRepository.findOne(providerId);
                Assert.assertNotNull(providerFromDB.getProviderId());
                Assert.assertNotNull(providerFromDB.getDistrict());
                Assert.assertNotNull(providerFromDB.getPrimaryMobile());
                return null;
            }
        });
    }

    @Test
    @Transactional
    public void shouldReturnProviderByProviderId() {
        providerRepository.save(provider);
        Provider providerFromDB = providerRepository.findByProviderId(providerId);

        Assert.assertNotNull(providerId);
        assertEquals(provider.getId(), providerFromDB.getId());
    }

    @After
    public void tearDown() {
        providerRepository.deleteAll();
    }
}
