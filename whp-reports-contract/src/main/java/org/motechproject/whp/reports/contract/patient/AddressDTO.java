package org.motechproject.whp.reports.contract.patient;

import lombok.Data;

@Data
public class AddressDTO {
    private String location;
    private String landmark;
    private String block;
    private String village;
    private String district;
    private String state;
}
