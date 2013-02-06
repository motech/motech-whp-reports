package org.motechproject.whp.reports.domain.patient;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@Table(name = "patient_address", uniqueConstraints = {@UniqueConstraint(columnNames = {"address_pk"})})
public class Address {

    @Id
    @Column(name = "address_pk")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "location")
    private String location;

    @Column(name = "landmark")
    private String landmark;

    @Column(name = "block")
    private String block;

    @Column(name = "village")
    private String village;

    @Column(name = "district")
    private String district;

    @Column(name = "state")
    private String state;

}
