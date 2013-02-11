package org.motechproject.whp.reports.contract.patient;

import lombok.Data;

import java.io.Serializable;

@Data
public class AddressDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String location;
    private String landmark;
    private String block;
    private String village;
    private String district;
    private String state;
}
