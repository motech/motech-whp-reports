package org.motechproject.whp.reports.contract.adherence;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class AdherenceRecordDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String patientId;
    private String therapyId;
    private Date pillDate;
    private String pillStatus;
    private String tbId;
    private String providerId;
    private String district;
}
