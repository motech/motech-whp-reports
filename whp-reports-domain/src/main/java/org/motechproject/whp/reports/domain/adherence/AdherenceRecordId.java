package org.motechproject.whp.reports.domain.adherence;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class AdherenceRecordId implements Serializable {

    private String patientId;
    private String therapyId;
    private Date pillDate;

    public AdherenceRecordId(String patientId, String therapyId, Date pillDate) {
        this.patientId = patientId;
        this.therapyId = therapyId;
        this.pillDate = pillDate;
    }

    public AdherenceRecordId() {
    }
}