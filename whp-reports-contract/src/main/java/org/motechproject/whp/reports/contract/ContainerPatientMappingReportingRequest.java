package org.motechproject.whp.reports.contract;

import lombok.Data;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.Date;

@Data
public class ContainerPatientMappingReportingRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    private String containerId;
    private String patientId;
    private String tbId;
    private String mappingInstance;
    private Date consultationDate;
    private String status;
    private String reasonForClosure;
    private DateTime closureDate;
    private String diagnosis;
}
