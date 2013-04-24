package org.motechproject.whp.reports.query;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.motechproject.whp.reports.domain.dimension.Provider;
import org.motechproject.whp.reports.repository.ProviderRepository;
import org.motechproject.whp.reports.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationReportingDomainContext.xml")
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@DbUnitConfiguration(databaseConnection = "dbUnitDatabaseConnection", dataSetLoader = CustomDataSetLoader.class)
public class ProviderPerformanceQueryScenariosIT {

    @Autowired
    ProviderService providerService;
    @Autowired
    ProviderRepository providerRepository;
    @Autowired
    BasicDataSource dataSource;

    @Test
    @DatabaseSetup(value = "providerPerformanceDataSetup.xml", type = DatabaseOperation.INSERT)
    @DatabaseTearDown(value = "providerPerformanceDataSetup.xml", type = DatabaseOperation.DELETE_ALL)
    public void shouldReturnProviderPerformanceByDistrict() throws Exception {

        List<Provider> provider = providerService.findAll();
        assertThat(provider.size(), is(2));

    }

    @After
    public void tearDown() throws Exception {
        providerRepository.deleteAll();
    }
}

