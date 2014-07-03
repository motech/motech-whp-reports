package org.motechproject.whp.reports.export.query.dao.query.builder;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.motechproject.whp.reports.export.query.dao.PatientReportType;
import org.motechproject.whp.reports.export.query.model.PatientReportRequest;

import static junit.framework.Assert.assertEquals;

public class PatientReportsQueryBuilderTest {

	
	@Test
	public void PatientCallLogReportRequestWithNoParams(){
		PatientReportRequest patientReportRequest = new PatientReportRequest();
		patientReportRequest.setReportType(PatientReportType.CALL_LOG);
		String query = new PatientReportsQueryBuilder(patientReportRequest).build();
		assertEquals(false,StringUtils.contains(query, "where"));
	}
}
