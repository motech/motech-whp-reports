package org.motechproject.whp.reports.domain.dimension;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "provider", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Provider {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "provider_id")
    private String providerId;

    @Column(name = "primary_mobile")
    private String primaryMobile;

    @Column(name = "secondary_mobile")
    private String secondaryMobile;

    @Column(name = "tertiary_mobile")
    private String tertiaryMobile;

    @Column(name = "district")
    private String district;
}
