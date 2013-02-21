package org.motechproject.whp.reports.domain.adherence;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "adherence_record", uniqueConstraints = {@UniqueConstraint(columnNames = {"patient_id", "therapy_id", "pill_date"})})
public class AdherenceRecord {

    @EmbeddedId
    AdherenceRecordId adherenceRecordId = new AdherenceRecordId();

    @Column(name = "pill_status")
    private String pillStatus;

    @Column(name = "tb_id")
    private String tbId;

    @Column(name = "provider_id")
    private String providerId;

    @Column(name = "district")
    private String district;


    public String getPatientId() {
        return adherenceRecordId.getPatientId();
    }

    public void setPatientId(String patientId) {
        adherenceRecordId.setPatientId(patientId);
    }

    public String getTherapyId() {
        return  adherenceRecordId.getTherapyId();
    }

    public void setTherapyId(String therapyId) {
        adherenceRecordId.setTherapyId(therapyId);
    }

    public Date getPillDate() {
        return adherenceRecordId.getPillDate();
    }

    public void setPillDate(Date pillDate) {
        adherenceRecordId.setPillDate(pillDate);
    }
}

