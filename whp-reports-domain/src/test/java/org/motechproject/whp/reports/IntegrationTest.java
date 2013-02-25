package org.motechproject.whp.reports;

import org.junit.runner.RunWith;
import org.motechproject.testing.utils.BaseUnitTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationReportingDomainContext.xml")
public abstract class IntegrationTest extends BaseUnitTest {

}