package org.motechproject.whp.reports.webservice.request;


import lombok.Data;

import java.io.Serializable;

@Data
public class AdherenceCaptureRequest implements Serializable {
    private String patientId;
    private String providerId;
    private int adherence;

    public AdherenceCaptureRequest() {
    }

    public AdherenceCaptureRequest(String providerId, String patientId, int adherence) {
        this.providerId = providerId;
        this.patientId = patientId;
        this.adherence = adherence;
    }
}

