package org.motechproject.whp.reports.contract.adherence;

import lombok.Data;

import java.sql.Date;

@Data
public class AdherenceRecordDTO {
    private String patientId;
    private String therapyId;
    private Date pillDate;
    private String pillStatus;
    private String tbId;
    private String providerId;
    private String district;
}
