package org.motechproject.whp.reports.contract;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ContainerPatientMappingReportingRequest implements Serializable {
    private String containerId;
    private String patientId;
    private String tbId;
    private String mappingInstance;
    private Date consultationDate;
    private String status;
    private String reasonForClosure;
    private Date closureDate;
}