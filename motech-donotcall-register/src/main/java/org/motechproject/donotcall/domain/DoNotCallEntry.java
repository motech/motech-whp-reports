package org.motechproject.donotcall.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "do_not_call", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class DoNotCallEntry {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "entity")
    private String entity;

    @Column(name = "entity_id")
    private String entityId;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_on")
    private Date updatedOn;

    @PreUpdate
    @PrePersist
    protected void onUpdate() {
        updatedOn = new Date();
    }

}

