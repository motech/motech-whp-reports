package org.motechproject.whp.reports.repository;


import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Statistics;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.motechproject.whp.reports.builder.ContainerRecordBuilder;
import org.motechproject.whp.reports.domain.measure.ContainerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationReportingDomainContext.xml")
public class CachingIT {

    @Autowired
    ContainerRecordRepository containerRecordRepository;
    @Autowired
    AlternateDiagnosisRepository alternateDiagnosisRepository;
    @Autowired
    ReasonForClosureRepository reasonForClosureRepository;
    @Autowired
    TestContainerRecordService testContainerRecordService;

    private CacheManager CACHE_MANAGER_INSTANCE;
    private final String ALTERNATE_DIAGNOSIS_CACHE_NAME = "org.motechproject.whp.reports.domain.dimension.AlternateDiagnosis";
    private final String REASON_FOR_CLOSURE_CACHE_NAME = "org.motechproject.whp.reports.domain.dimension.ReasonForClosure";

    @Before
    public void setUp() throws Exception {
        CACHE_MANAGER_INSTANCE = CacheManager.getInstance();
        enableCacheStatistics();
    }

    @Test
    public void shouldLoadFromCache() {
        //load alternate diagnosis and reason for closure
        alternateDiagnosisRepository.findOne("1027");
        reasonForClosureRepository.findOne("1");

        //create test records
        containerRecordRepository.save(createContainerRecord("containerId1", "1", "1027"));
        containerRecordRepository.save(createContainerRecord("containerId2", "1", "1027"));
        containerRecordRepository.save(createContainerRecord("containerId3", "1", "1027"));

        //fetch multiple times
        List<ContainerRecord> containerRecordsFromDB = testContainerRecordService.allContainerRecords();
        testContainerRecordService.allContainerRecords();
        testContainerRecordService.allContainerRecords();

        //assert that cached objects are loaded
        assertEquals("1", containerRecordsFromDB.get(0).getReasonForClosure().getCode());
        assertEquals("1027", containerRecordsFromDB.get(0).getAlternateDiagnosis().getCode());

        //assert cache statistics
        assertThat(alternateDiagnosisCacheStatistics().getCacheHits(), is(3L));
        assertThat(reasonForClosureCacheStatistics().getCacheHits(), is(3L));
    }

    private Statistics reasonForClosureCacheStatistics() {
        return reasonForClosureCache().getStatistics();
    }

    private Statistics alternateDiagnosisCacheStatistics() {
        return alternateDiagnosisCache().getStatistics();
    }

    private void enableCacheStatistics() {
        alternateDiagnosisCache().setStatisticsEnabled(true);
        reasonForClosureCache().setStatisticsEnabled(true);
    }

    private Cache reasonForClosureCache() {
        return CACHE_MANAGER_INSTANCE.getCache(REASON_FOR_CLOSURE_CACHE_NAME);
    }

    private Cache alternateDiagnosisCache() {
        return CACHE_MANAGER_INSTANCE.getCache(ALTERNATE_DIAGNOSIS_CACHE_NAME);
    }

    private ContainerRecord createContainerRecord(String containerId, String reasonForClosure, String alternateDiagnosis) {
        return new ContainerRecordBuilder()
                .withDefaults()
                .withContainerId(containerId)
                .withReasonForClosureCode(reasonForClosure)
                .withAlternateDiagnosisCode(alternateDiagnosis)
                .build();
    }

    @After
    public void tearDown() {
        containerRecordRepository.deleteAll();
    }
}
