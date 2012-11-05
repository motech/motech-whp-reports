package org.motechproject.whp.reports.contract;

import lombok.Data;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.Date;

@Data
public class ContainerStatusReportingRequest implements Serializable {
    private String containerId;
    private String status;
    private String reasonForClosure;
    private String diagnosis;
    private String alternateDiagnosisCode;
    private Date consultationDate;
    private DateTime closureDate;
}
