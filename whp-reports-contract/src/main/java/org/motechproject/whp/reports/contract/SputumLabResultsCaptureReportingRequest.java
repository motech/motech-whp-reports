package org.motechproject.whp.reports.contract;

import lombok.Data;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.Date;

@Data
public class SputumLabResultsCaptureReportingRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    private String containerId;
    private Date smearTestDate1;
    private String smearTestResult1;
    private Date smearTestDate2;
    private String smearTestResult2;
    private String cumulativeResult;
    private String labName;
    private String labNumber;
    private DateTime labResultsCapturedOn;
}
