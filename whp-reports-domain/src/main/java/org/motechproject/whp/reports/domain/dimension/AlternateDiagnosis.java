package org.motechproject.whp.reports.domain.dimension;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "alternate_diagnosis", uniqueConstraints = {@UniqueConstraint(columnNames = {"code", "text"})})
public class AlternateDiagnosis {

    @Column(name = "text")
    private String text;

    @Id
    @Column(name = "code")
    private String code;

    public AlternateDiagnosis() {
    }

    public AlternateDiagnosis(String code, String text) {
        this.text = text;
        this.code = code;
    }
}
