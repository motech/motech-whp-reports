package org.motechproject.whp.reports.contract.provider;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProviderDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String providerId;
    private String primaryMobile;
    private String secondaryMobile;
    private String tertiaryMobile;
    private String district;
}
