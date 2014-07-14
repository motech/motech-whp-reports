package org.motechproject.whp.reports.export.query.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.io.OutputStream;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.whp.reports.export.query.builder.ProviderReportBuilder;
import org.motechproject.whp.reports.export.query.dao.PatientReportType;
import org.motechproject.whp.reports.export.query.dao.ProviderReportType;
import org.motechproject.whp.reports.export.query.model.PatientReportRequest;
import org.motechproject.whp.reports.export.query.model.ProviderReportRequest;

public class ProviderReportsControllerTest {

	 @Mock
	    ProviderReportBuilder providerReportBuilder;

	    private ProviderReportsController providerReportsController;

	    @Before
	    public void setUp() {
	        initMocks(this);
	        providerReportsController = new ProviderReportsController(providerReportBuilder);
	    }

	    @Test
	    public void shouldCreateProviderReminderCallLogReport() throws Exception {

	    	ProviderReportRequest providerReportRequest = new ProviderReportRequest();
	        providerReportRequest.setDistrict("district");
	        providerReportRequest.setFrom("2012-01-01");
	        providerReportRequest.setTo("2012-01-31");
	        providerReportRequest.setReportType(ProviderReportType.REMINDER_CALL_LOG);

	        standaloneSetup(providerReportsController).build()
	                .perform(get("/providerreports/providerReminderCallLogReport.xls")
	                        .param("district", "district")
	                        .param("from", providerReportRequest.getFrom())
	                        .param("to", providerReportRequest.getTo()))
	                .andExpect(status().isOk())
	                .andExpect(header().string(PatientReportsController.CONTENT_DISPOSITION, "inline; filename=providerReminderCallLogReport.xls"))
	                .andExpect(content().contentType(PatientReportsController.APPLICATION_VND_MS_EXCEL));

	        verify(providerReportBuilder).buildProviderReminderCallLogReport(eq(providerReportRequest), any(OutputStream.class));
	    }
}
