package org.motechproject.whp.reports.domain.dimension;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "district", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class District {

    @Id
    @Column(name = "name")
    private  String name;
}
