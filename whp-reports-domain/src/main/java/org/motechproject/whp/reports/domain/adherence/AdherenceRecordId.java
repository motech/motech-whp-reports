package org.motechproject.whp.reports.domain.adherence;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.sql.Date;

@Embeddable
@Data
public class AdherenceRecordId implements Serializable {

    @Column(name = "patient_id")
    private String patientId;

    @Column(name = "therapy_id")
    private String therapyId;

    @Column(name = "pill_date")
    private Date pillDate;
}