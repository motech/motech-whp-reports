package org.motechproject.whp.reports.domain.adherence;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "adherence_record", uniqueConstraints = {@UniqueConstraint(columnNames = {"patient_id", "therapy_id", "pill_date"})})
@IdClass(AdherenceRecordId.class)
public class AdherenceRecord {

    @Id
    @Column(name = "patient_id")
    private String patientId;

    @Id
    @Column(name = "therapy_id")
    private String therapyId;

    @Id
    @Column(name = "pill_date")
    private Date pillDate;

    @Column(name = "pill_status")
    private String pillStatus;

    @Column(name = "tb_id")
    private String tbId;

    @Column(name = "provider_id")
    private String providerId;

    @Column(name = "district")
    private String district;
}

