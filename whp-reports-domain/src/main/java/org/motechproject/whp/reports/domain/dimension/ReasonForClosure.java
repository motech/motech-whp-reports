package org.motechproject.whp.reports.domain.dimension;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "reasons_for_closure", uniqueConstraints = {@UniqueConstraint(columnNames = {"code", "text"})})
public class ReasonForClosure {

    @Column(name = "text")
    private String text;

    @Id
    @Column(name = "code")
    private String code;

    public ReasonForClosure() {
    }

    public ReasonForClosure(String code, String text) {
        this.code = code;
        this.text = text;
    }
}

