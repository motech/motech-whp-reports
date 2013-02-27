package org.motechproject.whp.reports.export.query.builder;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.testing.utils.BaseUnitTest;
import org.motechproject.util.DateUtil;
import org.motechproject.whp.reports.date.WHPDateTime;
import org.motechproject.whp.reports.export.query.service.ExcelExporter;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class ReportBuilderTest extends BaseUnitTest{

    @Mock
    ExcelExporter excelExporter;
    @Mock
    OutputStream outputStream;

    ReportBuilder reportBuilder;

    @Before
    public void setUp() {
        initMocks(this);
        reportBuilder = new ReportBuilder(excelExporter);
    }

    @Test
    public void shouldCreateWorkBookAndWriteToOutputStream() throws IOException {
        DateTime now = DateUtil.now();
        mockCurrentDate(now);
        String generatedOn = WHPDateTime.date(now).value();
        String templateFileName = "template";
        Map params = new HashMap();

        reportBuilder.build(outputStream, params, templateFileName);

        verify(excelExporter).export(templateFileName, params);
        verify(outputStream).flush();
        assertThat((String) params.get(ReportBuilder.GENERATED_ON), is(generatedOn));
    }
}
