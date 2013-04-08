package org.motechproject.whp.reports.domain.dimension;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "district", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class District {

    @Id
    @Column(name = "district_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private  String name;
}
