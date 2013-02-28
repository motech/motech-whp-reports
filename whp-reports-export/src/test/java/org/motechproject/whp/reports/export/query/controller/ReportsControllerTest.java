package org.motechproject.whp.reports.export.query.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.whp.reports.export.query.builder.ReportBuilder;

import java.io.OutputStream;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class ReportsControllerTest {

    private ReportsController reportsController;

    @Mock
    ReportBuilder reportBuilder;
    private String reportName = "someReport";;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        when(reportBuilder.getReportName()).thenReturn(reportName);

        Set reportBuilders = new HashSet();
        reportBuilders.add(reportBuilder);
        reportsController = new ReportsController(reportBuilders);
    }

    @Test
    public void shouldExportGivenReport() throws Exception {
        standaloneSetup(reportsController).build()
                .perform(get("/reports/" + reportName))
                .andExpect(status().isOk())
                .andExpect(header().string(ReportsController.CONTENT_DISPOSITION, "inline; filename=" + reportName + ".xls"))
                .andExpect(content().contentType(ReportsController.APPLICATION_VND_MS_EXCEL));

        verify(reportBuilder).build(any(OutputStream.class));
    }

}

