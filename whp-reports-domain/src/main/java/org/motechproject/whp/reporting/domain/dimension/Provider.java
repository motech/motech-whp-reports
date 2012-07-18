package org.motechproject.whp.reporting.domain.dimension;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "provider")
public class Provider {

    @Id
    @Column(name = "providerId")
    private String providerId;

    @Column(name = "primaryMobile")
    private String primaryMobile;

    @Column(name = "secondaryMobile")
    private String secondaryMobile;

    @Column(name = "tertiaryMobile")
    private String tertiaryMobile;

    @Column(name = "district")
    private String district;
}
