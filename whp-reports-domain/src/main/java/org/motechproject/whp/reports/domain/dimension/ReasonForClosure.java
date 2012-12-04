package org.motechproject.whp.reports.domain.dimension;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "reasons_for_closure", schema = "whp_reports", uniqueConstraints = {@UniqueConstraint(columnNames = {"code", "text"})})
public class ReasonForClosure {

    @Column(name = "text")
    private String text;

    @Id
    @Column(name = "code")
    private String code;

    public ReasonForClosure() {
    }
}

